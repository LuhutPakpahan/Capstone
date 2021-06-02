package com.b21cap0398.acnescan.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivityHomeNavigationDrawerBinding
import com.b21cap0398.acnescan.ui.editprofile.EditProfileActivity
import com.b21cap0398.acnescan.ui.login.LoginActivity
import com.b21cap0398.acnescan.ui.result.ResultActivity
import com.b21cap0398.acnescan.ui.uploaddata.UploadDataActivity
import com.b21cap0398.acnescan.utils.RequestCodes
import com.b21cap0398.acnescan.utils.dummydata.DummyArticle
import com.b21cap0398.acnescan.utils.dummydata.DummyCommonAcne
import com.b21cap0398.acnescan.viewmodel.ViewModelFactory
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

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        setMainButtonOnClickListener()
        setEditProfileOnClickListener()
        setDrawerOnClickListener()

        setMostCommonAcneAdapter()
        setDailyReadAdapter()

        setNameProfile()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setNameProfile() {
        viewModel.getUserInformation(auth.currentUser?.email!!).observe(this, {
            val name = it.first_name
            binding.tvProfileName.text = "Hello, " + name
            val header = binding.navView.getHeaderView(0)
            val fullName: TextView = header.findViewById(R.id.tv_header_profile_name)
            fullName.text = it.first_name + " " + it.last_name
            hideLoading()
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RequestCodes.TAKE_GALLERY_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val filepath = data.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath) as Bitmap

            UploadDataActivity.bitmap = bitmap
            startActivity(Intent(this, UploadDataActivity::class.java))
        }


        if (requestCode == RequestCodes.CAPTURE_IMAGE && data != null) {
            val captureImage = data.extras?.get("data") as Bitmap
            UploadDataActivity.bitmap = captureImage
            startActivity(Intent(this, UploadDataActivity::class.java))
        }
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
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
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
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, RequestCodes.CAPTURE_IMAGE)
        }

        binding.civUpload.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Choose a picture"),
                RequestCodes.TAKE_GALLERY_IMAGE
            )
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return when (item.itemId) {
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
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
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