package com.b21cap0398.acnescan.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository

class EditProfileViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUserInformation(email: String) : LiveData<UserInformation> = mainRepository.getUserInformation(email)
}