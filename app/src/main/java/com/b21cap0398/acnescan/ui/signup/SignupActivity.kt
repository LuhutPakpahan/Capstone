package com.b21cap0398.acnescan.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivitySignupBinding
import com.b21cap0398.acnescan.utils.helper.UserValidationHelper
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val items = listOf<String>("Man", "Woman")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setGenderItems()
        setBackButtonOnClickListener()
        setSignupOnClickListener()
    }

    private fun setSignupOnClickListener() {
        binding.apply {
            civSignUp.setOnClickListener {

                showLoading()

                val firstName = tfFirstName.editText?.text.toString()
                val lastName = tfLastName.editText?.text.toString()
                val gender = tfGender.editText?.text.toString()
                val age = tfAge.editText?.text.toString()
                val email = tfEmail.editText?.text.toString()
                val password = tfPassword.editText?.text.toString()
                val confirmPassword = tfConfirmPassword.editText?.text.toString()

                if (signupIsValid(firstName, lastName, gender, age, email, password, confirmPassword) && cbAgreement.isChecked) {
                    singupToFirebase(email, password)
                }
                else {
                    // if first name is empty
                    if (!UserValidationHelper.isValidField(firstName))
                        showFieldErrorWithCounter(tfFirstName, getString(R.string.first_name_should_be_filled))
                    else hideFieldErrorWithCounter(tfFirstName)

                    // if last name is empty
                    if (!UserValidationHelper.isValidField(lastName))
                        showFieldErrorWithCounter(tfLastName, getString(R.string.last_name_should_be_filled))
                    else hideFieldErrorWithCounter(tfLastName)

                    // if gender is empty
                    if (!UserValidationHelper.isValidField(gender))
                        showFieldError(tfGender, getString(R.string.gender_should_be_filled))
                    else hideFieldError(tfGender)

                    // if age is empty
                    if (!UserValidationHelper.isValidField(age))
                        showFieldError(tfAge, getString(R.string.age_should_be_filled))
                    else hideFieldError(tfAge)

                    // if email is not valid
                    if (!UserValidationHelper.isValidEmail(email))
                        showFieldError(tfEmail, getString(R.string.email_is_not_valid))
                    else hideFieldError(tfEmail)

                    // if password is not valid
                    if (!UserValidationHelper.isValidPassword(password))
                        showFieldError(tfPassword, getString(R.string.password_must_be))
                    else hideFieldError(tfPassword)

                    // if confirm password is not valid
                    if (!UserValidationHelper.isValidPassword(confirmPassword))
                        showFieldError(tfConfirmPassword, getString(R.string.password_must_be))

                    // if confirm password is different from password
                    else if (confirmPassword != password)
                        showFieldError(tfConfirmPassword, getString(R.string.confirm_password_different))
                    else hideFieldError(tfConfirmPassword)
                }
                hideLoading()
            }
        }
    }

    private fun singupToFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@SignupActivity) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@SignupActivity, "${auth.currentUser?.email} has been created", Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }
                else {
                    Toast.makeText(this@SignupActivity, "Sign up failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun setBackButtonOnClickListener() {
        binding.civBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setGenderItems() {
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (binding.tfGender.editText as AutoCompleteTextView).setAdapter(adapter)
    }

    private fun signupIsValid(firstName: String, lastName: String, gender: String, age: String, email: String, password: String, confirmPassword: String) : Boolean {
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

    private fun showLoading() {
        val loadingScreen = binding.incLoading.root
        loadingScreen.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        val loadingScreen = binding.incLoading.root
        loadingScreen.visibility = View.GONE
    }

    private fun<T> showFieldError(v: T, errorText: String) {
        when(v) {
            is TextInputLayout -> {
                v.isErrorEnabled = true
                v.error = errorText
            }
        }
    }

    private fun<T> hideFieldError(v: T) {
        when(v) {
            is TextInputLayout -> {
                v.isErrorEnabled = false
            }
        }
    }

    private fun<T> showFieldErrorWithCounter(v: T, errorText: String) {
        when(v) {
            is TextInputLayout -> {
                v.isErrorEnabled = true
                v.isCounterEnabled = false
                v.error = errorText
            }
        }
    }

    private fun<T> hideFieldErrorWithCounter(v: T) {
        when(v) {
            is TextInputLayout -> {
                v.isErrorEnabled = false
                v.isCounterEnabled = true
            }
        }
    }
}