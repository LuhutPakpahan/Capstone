package com.b21cap0398.acnescan.ui.specificcommonacne

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.b21cap0398.acnescan.data.source.local.entity.MedicineInformation
import com.b21cap0398.acnescan.databinding.ActivitySpecificCommonAcneBinding
import com.b21cap0398.acnescan.ui.specificdetail.AcneImagesAdapter
import com.b21cap0398.acnescan.ui.specificdetail.RecomendedMedicineAdapter
import com.b21cap0398.acnescan.viewmodel.ViewModelFactory

class SpecificCommonAcneActivity : AppCompatActivity() {

    companion object {
        const val ACNE_NAME = "acne_name"
    }

    private lateinit var binding: ActivitySpecificCommonAcneBinding

    private lateinit var acneName: String
    private lateinit var acnePossibility: String

    private lateinit var acneImagesAdapter: AcneImagesAdapter
    private lateinit var recommendedMedicineAdapter: RecomendedMedicineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecificCommonAcneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[SpecificCommonAcneViewModel::class.java]

        val intent = intent
        acneName = intent.getStringExtra(ACNE_NAME).toString()

        binding.tvAcneName.text = acneName

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

        setBackButtonOnClickListener()
    }

    private fun setRecommendedMedicines(list: List<MedicineInformation>) {
        recommendedMedicineAdapter = RecomendedMedicineAdapter()
        recommendedMedicineAdapter.setList(list)
        binding.rvRecomendedMedicine.apply {
            layoutManager = LinearLayoutManager(
                this@SpecificCommonAcneActivity,
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
                this@SpecificCommonAcneActivity,
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
}