package com.b21cap0398.acnescan.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.di.Injection

class ViewModelFactory private constructor(private val mainRepository: MainRepository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMainRepository())
            }
    }
}