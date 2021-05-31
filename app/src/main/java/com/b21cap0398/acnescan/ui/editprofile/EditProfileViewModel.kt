package com.b21cap0398.acnescan.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.data.source.local.entity.UserInformation

class EditProfileViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUserInformation(email: String) : LiveData<UserInformation> = mainRepository.getUserInformation(email)

    fun setUserInformation(email: String, firstName: String, lastName: String, age: Long, gender: String) =
        mainRepository.setUserInformation(email = email, firstName = firstName, lastName = lastName, age = age, gender = gender)
}