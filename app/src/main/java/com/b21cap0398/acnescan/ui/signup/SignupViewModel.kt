package com.b21cap0398.acnescan.ui.signup

import androidx.lifecycle.ViewModel
import com.b21cap0398.acnescan.data.source.MainRepository

class SignupViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun setUserInformation(
        email: String,
        firstName: String,
        lastName: String,
        age: Long,
        gender: String
    ) = mainRepository.setUserInformation(email, firstName, lastName, age, gender)
}