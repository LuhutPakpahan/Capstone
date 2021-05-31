package com.b21cap0398.acnescan.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.data.source.local.entity.AcneScanResult
import com.b21cap0398.acnescan.data.source.local.entity.Possibility

class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getAcneScanResult(email: String, result_id: String): LiveData<AcneScanResult> = mainRepository.getAcneScanResult(email, result_id)

    fun getAllPossibilites(email: String, result_id: String): LiveData<List<Possibility>> = mainRepository.getAllPossibilites(email, result_id)
}