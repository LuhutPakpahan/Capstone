package com.b21cap0398.acnescan.ui.editprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivityEditProfileBinding
import com.b21cap0398.acnescan.utils.RequestCodes

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.civBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.civProfilePicture.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Choose a picture"), RequestCodes.TAKE_GALLERY_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            RequestCodes.TAKE_GALLERY_IMAGE -> {
                val selectedImage = data?.data
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
                binding.civProfilePicture.setImageBitmap(bitmap)
            }
        }
    }
}