package com.b21cap0398.acnescan.ui.uploaddata

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.b21cap0398.acnescan.data.source.local.entity.AcneScanResult
import com.b21cap0398.acnescan.data.source.local.entity.Possibility
import com.b21cap0398.acnescan.databinding.ActivityUploadDataBinding
import com.b21cap0398.acnescan.ui.result.ResultActivity
import com.b21cap0398.acnescan.utils.helper.ProgressBarOperator
import com.b21cap0398.acnescan.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UploadDataActivity : AppCompatActivity() {

    companion object {
        var bitmap: Bitmap? = null
    }

    private lateinit var binding: ActivityUploadDataBinding

    private lateinit var viewModel: UploadDataViewModel

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ProgressBarOperator.assignDescriptionTextView(binding.tvUploadDescription)
        ProgressBarOperator.assignProgressBar(binding.pbUploadProgress)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[UploadDataViewModel::class.java]

        val randomString = getRandomString(10)

        val currentTime = LocalDateTime.now()
        val date = currentTime.format(DateTimeFormatter.ISO_DATE)

        // Predicting the acne
        // Acnes result down here ....
        val possibilities = listOf<Possibility>(
            Possibility(acne_name = "Nodules", possibility = 80),
            Possibility(acne_name = "Nodules", possibility = 70),
            Possibility(acne_name = "Nodules", possibility = 60),
        )

        val acneScanResult = AcneScanResult(
            result_id = randomString,
            image_path = "users/${auth.currentUser?.email!!}/results/${randomString}/${randomString}.jpg",
            date = date.toString(),
            status = "accepted"
        )


        ProgressBarOperator.setProgressBarValue(0)
        ProgressBarOperator.setDescrtiptionTextView("Starting to upload")
        viewModel.setResultPhoto(bitmap!!, auth.currentUser?.email!!, randomString).observe(this, {
            startActivity(Intent(this, ResultActivity::class.java))
            finish()
        })

        viewModel.setScanResultAndPossibilites(
            auth.currentUser?.email!!,
            randomString,
            acneScanResult,
            possibilities
        )
    }

    fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    override fun onDestroy() {
        super.onDestroy()
        bitmap = null
    }
}