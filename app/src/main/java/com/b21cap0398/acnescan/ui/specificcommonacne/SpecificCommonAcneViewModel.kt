package com.b21cap0398.acnescan.ui.specificcommonacne

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.data.source.local.entity.AcneInformation

class SpecificCommonAcneViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getAcneInformationById(acneId: String): LiveData<AcneInformation> = mainRepository.getAcneInformationById(acneId)
}