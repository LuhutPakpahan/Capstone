package com.b21cap0398.acnescan.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.data.source.local.entity.AcneScanResult

class ResultViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getAllAcceptedAcneScanResult(email: String) : LiveData<List<AcneScanResult>> = mainRepository.getAllAcceptedAcneScanResult(email)
    fun getAllPendingAcneScanResult(email: String) : LiveData<List<AcneScanResult>> = mainRepository.getAllPendingAcneScanResult(email)
    fun getAllRejectedAcneScanResult(email: String) : LiveData<List<AcneScanResult>> = mainRepository.getAllRejectedAcneScanResult(email)
}