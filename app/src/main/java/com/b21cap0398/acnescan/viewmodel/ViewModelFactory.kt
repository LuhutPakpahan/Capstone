package com.b21cap0398.acnescan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.di.Injection
import com.b21cap0398.acnescan.ui.detail.DetailViewModel
import com.b21cap0398.acnescan.ui.result.ResultViewModel
import com.b21cap0398.acnescan.ui.specificdetail.SpecificDetailViewModel

class ViewModelFactory private constructor(private val mainRepository: MainRepository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMainRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mainRepository) as T
            }

            modelClass.isAssignableFrom(SpecificDetailViewModel::class.java) -> {
                SpecificDetailViewModel(mainRepository) as T
            }

            modelClass.isAssignableFrom(ResultViewModel::class.java) -> {
                ResultViewModel(mainRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}