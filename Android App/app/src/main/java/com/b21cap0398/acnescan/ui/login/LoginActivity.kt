package com.b21cap0398.acnescan.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivityLoginBinding
import com.b21cap0398.acnescan.ui.forgotpassword.ForgotPasswordActivity
import com.b21cap0398.acnescan.ui.home.HomeActivity
import com.b21cap0398.acnescan.ui.signup.SignupActivity
import com.b21cap0398.acnescan.utils.UserValidationHelper
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    // Binding
    private lateinit var binding: ActivityLoginBinding

    // Firebase
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSigninOnClickListener()
        setSignupOnClickListener()
        setForgotPasswordOnClickListener()
    }

    private fun setSigninOnClickListener() {
        binding.btnSignIn.setOnClickListener {

            showLoading()

            binding.apply {

                val email = tfEmail.editText?.text.toString()
                val password = tfPassword.editText?.text.toString()

                if (UserValidationHelper.isValidPassword(password) &&
                    UserValidationHelper.isValidEmail(email)
                ) {
                    resetErrorState()
                    signinToFirebase(email, password)
                } else {
                    if (!UserValidationHelper.isValidEmail(email)) {
                        showFieldError(tfEmail, getString(R.string.email_is_not_valid))
                    } else {
                        hideFieldError(tfEmail)
                    }

                    if (!UserValidationHelper.isValidPassword(password)) {
                        showFieldError(tfPassword, getString(R.string.password_must_be))
                    } else {
                        hideFieldError(tfPassword)
                    }
                }

                finishLoading()
            }
        }
    }

    private fun setForgotPasswordOnClickListener() {
        binding.tvForgotYourPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setSignupOnClickListener() {
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }


    private fun finishLoading() {
        val loadingScreen = binding.incLoading.root
        loadingScreen.visibility = View.GONE
    }

    private fun showLoading() {
        val loadingScreen = binding.incLoading.root
        loadingScreen.visibility = View.VISIBLE
        binding.tvWrongEmailPassword.visibility = View.GONE
    }

    private fun <T> showFieldError(v: T, errorText: String? = null) {
        when (v) {
            is TextInputLayout -> {
                v.isErrorEnabled = true
                v.error = errorText
            }
            is TextView -> {
                v.visibility = View.VISIBLE
            }
        }
    }

    private fun <T> hideFieldError(v: T) {
        when (v) {
            is TextInputLayout -> {
                v.isErrorEnabled = false
            }
        }
    }

    private fun resetErrorState() {
        binding.apply {
            tfEmail.isErrorEnabled = false
            tfPassword.isErrorEnabled = false
        }
    }

    private fun signinToFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@LoginActivity) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    showFieldError(binding.tvWrongEmailPassword)
                }
            }
    }
}