package com.b21cap0398.acnescan.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivityHomeBinding
import com.b21cap0398.acnescan.databinding.ActivityHomeNavigationDrawerBinding
import com.b21cap0398.acnescan.ui.editprofile.EditProfileActivity
import com.b21cap0398.acnescan.ui.login.LoginActivity
import com.b21cap0398.acnescan.ui.result.ResultActivity
import com.b21cap0398.acnescan.utils.DummyArticle
import com.b21cap0398.acnescan.utils.DummyCommonAcne
import com.b21cap0398.acnescan.utils.RequestCodes
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener {

    // Binding
    private lateinit var binding: ActivityHomeNavigationDrawerBinding

    // Adapter for common acne and daily read
    private lateinit var mostCommonAcneAdapter: MostCommonAcneAdapter
    private lateinit var dailyReadAdapter: DailyReadAdapter

    // Firebase
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMainButtonOnClickListener()
        setEditProfileOnClickListener()
        setDrawerOnClickListener()

        setMostCommonAcneAdapter()
        setDailyReadAdapter()
    }

    private fun setDailyReadAdapter() {
        dailyReadAdapter = DailyReadAdapter()
        dailyReadAdapter.setDailyReadList(DummyArticle.addDummyArticle())
        binding.rvYourDailyRead.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
            adapter = dailyReadAdapter
        }
    }

    private fun setMostCommonAcneAdapter() {
        mostCommonAcneAdapter = MostCommonAcneAdapter()
        mostCommonAcneAdapter.setListCommonAcnes(DummyCommonAcne.addDummyAcnes())
        binding.rvMostCommonAcnes.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mostCommonAcneAdapter
        }
    }

    private fun setDrawerOnClickListener() {
        val drawerButton: ImageView = findViewById(R.id.btn_drawer)

        drawerButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navView.setNavigationItemSelectedListener(this)

        binding.navView.getHeaderView(0).setOnClickListener(this)
    }

    private fun setEditProfileOnClickListener() {
        binding.ivPictureProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setMainButtonOnClickListener() {
        binding.civResult.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }

        binding.civTakeAPhoto.setOnClickListener {

        }

        binding.civUpload.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Choose a picture"), RequestCodes.TAKE_GALLERY_IMAGE)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return when(item.itemId) {
            R.id.nav_log_out -> {
                auth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                true
            }

            R.id.nav_result -> {
                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
                true
            }

            else -> true
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_edit_profile -> {
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
            }
            else -> {

            }
        }
    }
}