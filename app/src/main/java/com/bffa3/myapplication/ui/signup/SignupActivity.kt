package com.bffa3.myapplication.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bffa3.myapplication.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}