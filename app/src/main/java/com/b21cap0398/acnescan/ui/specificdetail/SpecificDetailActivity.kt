package com.b21cap0398.acnescan.ui.specificdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.data.source.local.entity.MedicineInformation
import com.b21cap0398.acnescan.databinding.ActivitySpecificDetailBinding
import com.b21cap0398.acnescan.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import java.text.DecimalFormat

class SpecificDetailActivity : AppCompatActivity() {

    companion object {
        const val ACNE_NAME = "acneName"
        const val ACNE_POSSIBILITY = "acnePossibility"
    }

    // Binding
    private lateinit var binding: ActivitySpecificDetailBinding

    // Adapter
    private lateinit var acneImagesAdapter: AcneImagesAdapter
    private lateinit var recommendedMedicineAdapter: RecomendedMedicineAdapter

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private lateinit var acneName: String
    private lateinit var acnePossibility: String

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecificDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[SpecificDetailViewModel::class.java]

        val intent = intent
        acneName = intent.getStringExtra(ACNE_NAME).toString()
        val possibilityNum = intent.getDoubleExtra(ACNE_POSSIBILITY, 0.0)
        acnePossibility = DecimalFormat("##.#").format(possibilityNum)

        binding.tvAcneName.text = acneName
        binding.tvAcneNumberPossibility.text =
            getString(R.string.your_acne_is_similar_to_this_type_of_acne_about) + " " + acnePossibility + "%"

        viewModel.getAcneInformationById(acneName).observe(this, {
            binding.apply {
                tvAcneDescription.text = it.description
                tvCauseOfAcne.text = it.causes
                tvTipsToDeal.text = it.tips
                setAcneImagesAdapter(it.listImagePaths!!)
            }

            hideLoading()
        })

        setRecommendedMedicines(
            listOf(
                MedicineInformation("", "", ""),
                MedicineInformation("", "", ""),
                MedicineInformation("", "", ""),
                MedicineInformation("", "", ""),
                MedicineInformation("", "", ""),
                MedicineInformation("", "", ""),
                MedicineInformation("", "", "")
            )
        )

        setFeedbackButtonOnClickListener()
        setBackButtonOnClickListener()
    }

    private fun setFeedbackButtonOnClickListener() {
        binding.civFeedbackButton.setOnClickListener {
            createNewFeedbackDialog()
        }
    }

    private fun setRecommendedMedicines(list: List<MedicineInformation>) {
        recommendedMedicineAdapter = RecomendedMedicineAdapter()
        recommendedMedicineAdapter.setList(list)
        binding.rvRecomendedMedicine.apply {
            layoutManager = LinearLayoutManager(
                this@SpecificDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = recommendedMedicineAdapter
        }
    }

    private fun setAcneImagesAdapter(list: List<String>) {
        acneImagesAdapter = AcneImagesAdapter()
        acneImagesAdapter.setList(list)
        binding.rvAcneImages.apply {
            layoutManager = LinearLayoutManager(
                this@SpecificDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = acneImagesAdapter

            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(binding.rvAcneImages)
        }
    }

    private fun setBackButtonOnClickListener() {
        binding.civBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showLoading() {
        val loadingScreen = binding.incLoading.root
        loadingScreen.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        val loadingScreen = binding.incLoading.root
        loadingScreen.visibility = View.GONE
    }

    @SuppressLint("InflateParams")
    private fun createNewFeedbackDialog() {
        dialogBuilder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.feedback_popup, null)

        dialogBuilder.setView(view)
        dialog = dialogBuilder.create()
        dialog.show()

        view.findViewById<CardView>(R.id.civ_send_feedback).setOnClickListener {
            dialog.dismiss()
            createFeedbackSentDialog()
        }
    }

    private fun createFeedbackSentDialog() {
        dialogBuilder = AlertDialog.Builder(this)
        val sentView = layoutInflater.inflate(R.layout.feeback_sent_popup, null)

        dialogBuilder.setView(sentView)
        dialog = dialogBuilder.create()
        dialog.show()
    }
}