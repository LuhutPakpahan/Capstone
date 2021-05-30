package com.b21cap0398.acnescan.data.source.firebase

import com.b21cap0398.acnescan.utils.EndpointHelper

class FirebaseDataSource {
    companion object {
        @Volatile
        private var instance: FirebaseDataSource? = null

        fun getInstance(): FirebaseDataSource =
            instance ?: synchronized(this) {
                instance ?: FirebaseDataSource()
            }
    }

    suspend fun getResultsAcneScan(email: String):  {
        EndpointHelper.resultsScanRef(email).get().d
    }

    suspend fun uploadAcneImage() {}

    suspend fun getDailyArticleAcne() {}

    suspend fun getMostCommonAcne() {}

    suspend fun getUserProfile() {}

    suspend fun setUserProfile() {}
}