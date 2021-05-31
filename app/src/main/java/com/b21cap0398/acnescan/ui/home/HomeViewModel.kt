package com.b21cap0398.acnescan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.data.source.local.entity.CommonAcne
import com.b21cap0398.acnescan.data.source.local.entity.UserInformation

class HomeViewModel(private val mainRepository: MainRepository) : ViewModel() {

    //fun getMostCommonAcnes(): LiveData<List<CommonAcne>>

    //fun getDailyRead(): LiveData<List<DailyRead>>

    fun getUserInformation(email: String): LiveData<UserInformation> = mainRepository.getUserInformation(email)
}