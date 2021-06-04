package com.b21cap0398.acnescan.ui.editprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivityEditProfileBinding
import com.b21cap0398.acnescan.utils.RequestCodes
import com.b21cap0398.acnescan.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditProfileBinding

    private lateinit var viewModel: EditProfileViewModel

    private val items = listOf<String>("Man", "Woman")

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()
        setGenderItems()

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[EditProfileViewModel::class.java]

        viewModel.getUserInformation(auth.currentUser?.email!!).observe(this, {
            binding.apply {
                tfFirstName.editText?.setText(it.first_name)
                tfLastName.editText?.setText(it.last_name)
                tfAge.editText?.setText(it.age.toString())
                tfGender.editText?.setText(it.gender)
            }

            hideLoading()
        })

        binding.civBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.civEditProfile.setOnClickListener {
            val firstName = binding.tfFirstName.editText?.text
            val lastName = binding.tfLastName.editText?.text
            val gender = binding.tfGender.editText?.text
            val age = binding.tfAge.editText?.text.toString()
            viewModel.setUserInformation(
                email = auth.currentUser?.email!!,
                firstName = firstName.toString(),
                lastName = lastName.toString(),
                age = age.toLong(),
                gender = gender.toString()
            )

            Toast.makeText(this, "Profile has been successfully edited", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

        binding.civProfilePicture.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Choose a picture"), RequestCodes.TAKE_GALLERY_IMAGE)
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

    private fun setGenderItems() {
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (binding.tfGender.editText as AutoCompleteTextView).setAdapter(adapter)
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