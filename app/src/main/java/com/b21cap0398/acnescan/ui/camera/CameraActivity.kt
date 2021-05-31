package com.b21cap0398.acnescan.ui.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.b21cap0398.acnescan.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}