package com.b21cap0398.acnescan.di

import com.b21cap0398.acnescan.data.source.MainRepository
import com.b21cap0398.acnescan.data.source.firebase.FirebaseDataSource

object Injection {
    fun provideMainRepository(): MainRepository {
        val firebaseDataSource = FirebaseDataSource.getInstance()
        return MainRepository(firebaseDataSource)
    }
}