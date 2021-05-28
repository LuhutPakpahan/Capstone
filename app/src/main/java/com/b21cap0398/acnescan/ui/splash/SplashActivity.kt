package com.b21cap0398.acnescan.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.ui.home.HomeActivity
import com.b21cap0398.acnescan.ui.login.LoginActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            if (auth.currentUser != null) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2000)
    }
}