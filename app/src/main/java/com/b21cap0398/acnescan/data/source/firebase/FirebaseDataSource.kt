package com.b21cap0398.acnescan.data.source.firebase

class FirebaseDataSource {
    companion object {
        @Volatile
        private var instance: FirebaseDataSource? = null

        fun getInstance(): FirebaseDataSource =
            instance ?: synchronized(this) {
                instance ?: FirebaseDataSource()
            }
    }

    suspend fun getResultsAcneScan() {}

    suspend fun uploadAcneImage() {}

    suspend fun getDailyArticleAcne() {}

    suspend fun getMostCommonAcne() {}

    suspend fun getUserProfile() {}

    suspend fun setUserProfile() {}
}