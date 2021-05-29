package com.b21cap0398.acnescan.ui.specificdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.databinding.ActivitySpecificDetailBinding
import com.b21cap0398.acnescan.utils.DummyAcnePictures

class SpecificDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpecificDetailBinding
    private lateinit var adapter: SpecificDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecificDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SpecificDetailAdapter()
        adapter.setListPhoto(DummyAcnePictures.addDummyAcnePictures())
        binding.rvOtherAcneImage.apply {
            layoutManager = LinearLayoutManager(this@SpecificDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        binding.civBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}