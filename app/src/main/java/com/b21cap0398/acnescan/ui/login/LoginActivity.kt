package com.b21cap0398.acnescan.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivityLoginBinding
import com.b21cap0398.acnescan.ui.forgotpassword.ForgotPasswordActivity
import com.b21cap0398.acnescan.ui.home.HomeActivity
import com.b21cap0398.acnescan.ui.signup.SignupActivity
import com.b21cap0398.acnescan.utils.UserValidationHelper
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        FirebaseApp.initializeApp(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {

            val loadingScreen = binding.incLoading.root
            loadingScreen.visibility = View.VISIBLE
            binding.tvWrongEmailPassword.visibility = View.GONE

            val email = binding.tfEmail.editText?.text.toString()
            val password = binding.tfPassword.editText?.text.toString()

            if (UserValidationHelper.isValidPassword(password) &&
                UserValidationHelper.isValidEmail(email)
            ) {
                binding.tfEmail.isErrorEnabled = false
                binding.tfPassword.isErrorEnabled = false

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            binding.tvWrongEmailPassword.visibility = View.VISIBLE
                        }
                        loadingScreen.visibility = View.GONE
                    }
            } else {
                if (!UserValidationHelper.isValidEmail(email)) {
                    binding.tfEmail.isErrorEnabled = true
                    binding.tfEmail.error = getString(R.string.email_is_not_valid)
                } else {
                    binding.tfEmail.isErrorEnabled = false
                }

                if (!UserValidationHelper.isValidPassword(password)) {
                    binding.tfPassword.isErrorEnabled = true
                    binding.tfPassword.error = getString(R.string.password_must_be)
                } else {
                    binding.tfPassword.isErrorEnabled = true
                }
                loadingScreen.visibility = View.GONE
            }
        }

        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.tvForgotYourPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}