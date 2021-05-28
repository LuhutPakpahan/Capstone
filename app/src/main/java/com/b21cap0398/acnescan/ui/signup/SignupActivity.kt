package com.b21cap0398.acnescan.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.b21cap0398.acnescan.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}