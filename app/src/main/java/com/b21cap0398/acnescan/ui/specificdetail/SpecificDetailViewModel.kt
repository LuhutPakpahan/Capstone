package com.b21cap0398.acnescan.ui.specificdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.data.source.local.entity.AcneInformation
import com.b21cap0398.acnescan.data.source.local.entity.FeedbackForm

class SpecificDetailViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getAcneInformationById(acneId: String): LiveData<AcneInformation> = mainRepository.getAcneInformationById(acneId)

    fun setFeedback(feedbackForm: FeedbackForm) = mainRepository.setFeedback(feedbackForm)
}