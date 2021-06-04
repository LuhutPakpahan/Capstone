package com.b21cap0398.acnescan.utils.helper

import android.util.Patterns

object UserValidationHelper {

    const val PASSWORD_MIN_SIZE = 6

    fun isValidEmail(text: String?) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(text as CharSequence).matches()
    }

    fun isValidPassword(text: String?) : Boolean {
        val length = text?.length ?: 0
        return length > PASSWORD_MIN_SIZE
    }

    fun isValidField(text: String?) : Boolean = text != ""
}