package com.b21cap0398.acnescan.ui.specificdetail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivitySpecificDetailBinding
import com.b21cap0398.acnescan.utils.dummydata.DummyAcnePictures
import com.b21cap0398.acnescan.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class SpecificDetailActivity : AppCompatActivity() {

    companion object {
        const val ACNE_NAME = "acne_name"
        const val ACNE_POSSIBILITY = "acne_possibility"
    }

    // Binding
    private lateinit var binding: ActivitySpecificDetailBinding

    // Adapter
    private lateinit var adapter: SpecificDetailAdapter

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private lateinit var acne_name: String
    private lateinit var acne_possibility: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecificDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[SpecificDetailViewModel::class.java]

        val intent = intent
        acne_name = intent.getStringExtra(ACNE_NAME).toString()
        acne_possibility = intent.getLongExtra(ACNE_POSSIBILITY, 0).toString()

        binding.tvAcneName.text = acne_name
        binding.tvAcneNumberPossibility.text =
            getString(R.string.your_acne_is_similar_to_this_type_of_acne_about) + " " + acne_possibility + "%"

        viewModel.getAcneInformationById(acne_name).observe(this, {
            binding.apply {
                tvAcneDescription.text = it.description
                tvCauseOfAcne.text = it.causes
                tvTipsToDeal.text = it.tips
            }

            hideLoading()
        })

        setSpecificDetailAdapter()

        setBackButtonOnClickListener()
    }

    private fun setSpecificDetailAdapter() {
        adapter = SpecificDetailAdapter()
        adapter.setListPhoto(DummyAcnePictures.addDummyAcnePictures())
        binding.rvOtherAcneImage.apply {
            layoutManager = LinearLayoutManager(
                this@SpecificDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            this.adapter = adapter
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