package com.b21cap0398.acnescan.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.data.source.local.entity.Possibility
import com.b21cap0398.acnescan.databinding.ActivityDetailBinding
import com.b21cap0398.acnescan.ui.specificdetail.SpecificDetailActivity
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

        viewModel.getAllPossibilites(auth.currentUser?.email!!, result_id as String)
            .observe(this, { possibilities ->
                val sortedPossibilities = possibilities.sortedByDescending { it.possibility }
                val arraySortedPossibilities = ArrayList<Possibility>()
                arraySortedPossibilities.addAll(sortedPossibilities)
                val highestPossibility = sortedPossibilities[0]
                val highestPossibilityNumber = highestPossibility.possibility.toString()

                binding.tvHighestPossibilites.text =
                    getString(R.string.the_highest_possibilites_s) + " " + highestPossibilityNumber + "%"
                binding.apply {
                    val adapter = OtherPossibilitiesAdapter()
                    arraySortedPossibilities.removeAt(0)
                    adapter.setList(arraySortedPossibilities)
                    rvOtherAcnePossibility.layoutManager = LinearLayoutManager(
                        this@DetailActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    rvOtherAcnePossibility.adapter = adapter

                    adapter.setOnItemClickCallback(object :
                        OtherPossibilitiesAdapter.OnItemClickCallback {
                        override fun onItemClicked(data: Possibility) {
                            val intent =
                                Intent(this@DetailActivity, SpecificDetailActivity::class.java)
                            intent.putExtra(SpecificDetailActivity.ACNE_NAME, data.acne_name)
                            intent.putExtra(
                                SpecificDetailActivity.ACNE_POSSIBILITY,
                                data.possibility
                            )
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