package com.b21cap0398.acnescan.ui.uploaddata

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.data.source.local.entity.AcneScanResult
import com.b21cap0398.acnescan.data.source.local.entity.Possibility
import com.b21cap0398.acnescan.utils.helper.FirebaseStorageEndpointHelper

class UploadDataViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun setResultPhoto(bitmap: Bitmap, email: String, result_id: String): LiveData<String> =
        mainRepository.setResultPhoto(bitmap, email, result_id)

    fun setScanResultAndPossibilites(
        email: String,
        result_id: String,
        acneScanResult: AcneScanResult,
        possibilities: List<Possibility>
    ) = mainRepository.setScanResultAndPossibilites(email, result_id, acneScanResult, possibilities)
}