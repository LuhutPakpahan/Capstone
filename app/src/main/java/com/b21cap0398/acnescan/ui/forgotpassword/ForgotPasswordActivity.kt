package com.b21cap0398.acnescan.ui.forgotpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.ActivityForgotPasswordBinding
import com.b21cap0398.acnescan.ui.emailreset.EmailResetActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.civBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.btnSend.setOnClickListener {

            val loadingScreen = binding.incLoading.root
            loadingScreen.visibility = View.VISIBLE

            val email = binding.tfEmail.editText?.text.toString()
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, EmailResetActivity::class.java)
                        startActivity(intent)
                        finish()
                        loadingScreen.visibility = View.VISIBLE
                    }
                }
        }
    }
}