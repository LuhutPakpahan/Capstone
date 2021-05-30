package com.b21cap0398.acnescan.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository

class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getAcneScanResult(email: String, pos: Int): LiveData<AcneScanResult> = mainRepository.getAcneScanResult(email, pos)
}