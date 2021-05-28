package com.b21cap0398.acnescan.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivitySignupBinding
import com.b21cap0398.acnescan.utils.UserValidationHelper
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val items = listOf<String>("Man", "Woman")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (binding.tfGender.editText as AutoCompleteTextView).setAdapter(adapter)

        binding.civBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.civSignUp.setOnClickListener {
            val loadingScreen = binding.incLoading.root
            loadingScreen.visibility = View.VISIBLE

            val firstName = binding.tfFirstName.editText?.text.toString()
            val lastName = binding.tfLastName.editText?.text.toString()
            val gender = binding.tfGender.editText?.text.toString()
            val age = binding.tfAge.editText?.text.toString()
            val email = binding.tfEmail.editText?.text.toString()
            val password = binding.tfPassword.editText?.text.toString()
            val confirmPassword = binding.tfConfirmPassword.editText?.text.toString()

            if (registerIsValid(firstName, lastName, gender, age, email, password, confirmPassword) && binding.cbAgreement.isChecked) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "${auth.currentUser?.email} has been created", Toast.LENGTH_SHORT).show()
                            onBackPressed()
                        }
                        else {
                            Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
                        }
                        loadingScreen.visibility = View.VISIBLE
                    }
            }
            else {
                if (!UserValidationHelper.isValidField(firstName)) {
                    binding.apply {
                        tfFirstName.error = getString(R.string.first_name_should_be_filled)
                        tfFirstName.isErrorEnabled = true
                        tfFirstName.isCounterEnabled = false
                    }
                } else {
                    binding.apply {
                        tfFirstName.isErrorEnabled = false
                        tfFirstName.isCounterEnabled = true
                    }
                }

                if (!UserValidationHelper.isValidField(lastName)) {
                    binding.apply {
                        tfLastName.error = "Last name should be filled"
                        tfLastName.isErrorEnabled = true
                        tfLastName.isCounterEnabled = false
                    }
                } else {
                    binding.apply {
                        tfLastName.isErrorEnabled = false
                        tfLastName.isCounterEnabled = true
                    }
                }

                if (!UserValidationHelper.isValidField(gender)) {
                    binding.apply {
                        tfGender.error = getString(R.string.gender_should_be_filled)
                        tfGender.isErrorEnabled = true
                    }
                } else binding.tfGender.isErrorEnabled = false

                if (!UserValidationHelper.isValidField(age)) {
                    binding.apply {
                        tfAge.error = getString(R.string.age_should_be_filled)
                        tfAge.isErrorEnabled = true
                        tfAge.isCounterEnabled = false
                    }
                } else {
                    binding.apply {
                        tfAge.isErrorEnabled = false
                        tfAge.isCounterEnabled = true
                    }
                }

                if (!UserValidationHelper.isValidEmail(email)) {
                    binding.apply {
                        tfEmail.error = getString(R.string.email_is_not_valid)
                        tfPassword.isErrorEnabled = true
                    }
                } else binding.tfPassword.isErrorEnabled = false

                if (!UserValidationHelper.isValidPassword(password)) {
                    binding.apply {
                        tfPassword.error = getString(R.string.password_must_be)
                        tfPassword.isErrorEnabled = true
                    }
                } else binding.tfPassword.isErrorEnabled = false

                if (!UserValidationHelper.isValidPassword(confirmPassword)) {
                    binding.apply {
                        tfConfirmPassword.error = getString(R.string.password_must_be)
                        tfConfirmPassword.isErrorEnabled = true
                    }
                } else if (confirmPassword != password) {
                    binding.apply {
                        tfConfirmPassword.error = getString(R.string.confirm_password_different)
                        tfConfirmPassword.isErrorEnabled = true
                    }
                } else binding.tfConfirmPassword.isErrorEnabled = false
            }
            loadingScreen.visibility = View.GONE
        }
    }

    private fun registerIsValid(firstName: String, lastName: String, gender: String, age: String, email: String, password: String, confirmPassword: String) : Boolean {
        UserValidationHelper.apply {
            return isValidField(firstName) &&
                    isValidField(lastName) &&
                    isValidField(gender) &&
                    isValidField(age) &&
                    isValidEmail(email) &&
                    isValidPassword(password) &&
                    password == confirmPassword
        }
    }
}