package com.bffa3.myapplication.ui.specificdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bffa3.myapplication.R
import com.bffa3.myapplication.databinding.ActivitySpecificDetailBinding

class SpecificDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpecificDetailBinding
    private lateinit var adapter: SpecificDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecificDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SpecificDetailAdapter()

        binding.rvOtherAcneImage.apply {
            layoutManager = LinearLayoutManager(this@SpecificDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = adapter
        }
    }
}