package com.b21cap0398.acnescan.data.source

import com.b21cap0398.acnescan.data.source.firebase.FirebaseDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainRepository private constructor(private val firebaseDataSource: FirebaseDataSource): MainDataSource{

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(firebaseDataSource: FirebaseDataSource): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(firebaseDataSource)
            }
    }

    override fun getDailyArticleAcne() {
        CoroutineScope(IO).launch {
            firebaseDataSource.getDailyArticleAcne()
        }
    }

    override fun getMostCommonAcne() {
        CoroutineScope(IO).launch {
            firebaseDataSource.getMostCommonAcne()
        }
    }

    override fun getResultsAcneScan() {
        CoroutineScope(IO).launch {
            firebaseDataSource.getResultsAcneScan()
        }
    }

    override fun uploadAcneImage() {
        CoroutineScope(IO).launch {
            firebaseDataSource.setUserProfile()
        }
    }

    override fun getUserProfile() {
        CoroutineScope(IO).launch {
            firebaseDataSource.getUserProfile()
        }
    }

    override fun setUserProfile() {
        CoroutineScope(IO).launch {
            firebaseDataSource.setUserProfile()
        }
    }
}