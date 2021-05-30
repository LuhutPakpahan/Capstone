package com.b21cap0398.acnescan.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.data.source.local.entity.OtherPossibility
import com.b21cap0398.acnescan.databinding.ActivityDetailBinding
import com.b21cap0398.acnescan.ui.specificdetail.SpecificDetailActivity
import com.b21cap0398.acnescan.utils.dummydata.DummyOtherPossibilities

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.civBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.ivHighestPossibility.setOnClickListener {
            val intent = Intent(this, SpecificDetailActivity::class.java)
            startActivity(intent)
        }

        binding.apply {
            val adapter = OtherPossibilitiesAdapter()
            adapter.setList(DummyOtherPossibilities.getDummyOtherPossibilites())
            rvOtherAcnePossibility.layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            rvOtherAcnePossibility.adapter = adapter

            adapter.setOnItemClickCallback(object : OtherPossibilitiesAdapter.OnItemClickCallback {
                override fun onItemClicked(data: OtherPossibility) {
                    val intent = Intent(this@DetailActivity, SpecificDetailActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }
}