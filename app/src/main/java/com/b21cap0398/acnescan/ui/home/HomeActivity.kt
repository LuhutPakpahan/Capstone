package com.b21cap0398.acnescan.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivityHomeBinding
import com.b21cap0398.acnescan.databinding.ActivityHomeNavigationDrawerBinding
import com.b21cap0398.acnescan.utils.DummyArticle
import com.b21cap0398.acnescan.utils.DummyCommonAcne

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeNavigationDrawerBinding
    private lateinit var homeBinding: ActivityHomeBinding

    private lateinit var mostCommonAcneAdapter: MostCommonAcneAdapter
    private lateinit var dailyReadAdapter: DailyReadAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeNavigationDrawerBinding.inflate(layoutInflater)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val drawerButton: ImageView = findViewById(R.id.btn_drawer)

        drawerButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        mostCommonAcneAdapter = MostCommonAcneAdapter()
        mostCommonAcneAdapter.setListCommonAcnes(DummyCommonAcne.addDummyAcnes())
        binding.rvMostCommonAcnes.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mostCommonAcneAdapter
        }

        dailyReadAdapter = DailyReadAdapter()
        dailyReadAdapter.setDailyReadList(DummyArticle.addDummyArticle())
        binding.rvYourDailyRead.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
            adapter = dailyReadAdapter
        }
    }
}