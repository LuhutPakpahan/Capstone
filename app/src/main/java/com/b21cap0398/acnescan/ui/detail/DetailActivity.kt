package com.b21cap0398.acnescan.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.data.source.local.entity.AcneScanResult
import com.b21cap0398.acnescan.data.source.local.entity.OtherPossibility
import com.b21cap0398.acnescan.data.source.local.entity.Possibility
import com.b21cap0398.acnescan.databinding.ActivityDetailBinding
import com.b21cap0398.acnescan.ui.specificdetail.SpecificDetailActivity
import com.b21cap0398.acnescan.utils.dummydata.DummyOtherPossibilities
import com.b21cap0398.acnescan.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class DetailActivity : AppCompatActivity() {

    companion object {
        const val RESULT_ID = "result_id"
    }

    private lateinit var binding: ActivityDetailBinding

    private val auth = FirebaseAuth.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val intent = intent
        val result_id = intent.getStringExtra(RESULT_ID)

        viewModel.getAllPossibilites(auth.currentUser?.email!!, result_id as String).observe( this, {
            var highestPossibility = Possibility("", 0)
            for (possibility in it) {
                if (highestPossibility.possibility < possibility.possibility) {
                    highestPossibility = possibility
                }
            }

            val highestPossibilityNumber = highestPossibility.possibility.toString()
            binding.tvHighestPossibilites.text = getString(R.string.the_highest_possibilites_s) + " " + highestPossibilityNumber + "%"

            binding.apply {
                val adapter = OtherPossibilitiesAdapter()
                adapter.setList(it)
                rvOtherAcnePossibility.layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
                rvOtherAcnePossibility.adapter = adapter

                adapter.setOnItemClickCallback(object : OtherPossibilitiesAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Possibility) {
                        val intent = Intent(this@DetailActivity, SpecificDetailActivity::class.java)
                        intent.putExtra(SpecificDetailActivity.ACNE_NAME, data.acne_name)
                        intent.putExtra(SpecificDetailActivity.ACNE_POSSIBILITY, data.possibility)
                        startActivity(intent)
                    }
                })
            }

            hideLoading()
        })

        binding.civBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.ivHighestPossibility.setOnClickListener {
            val intent = Intent(this, SpecificDetailActivity::class.java)
            startActivity(intent)
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