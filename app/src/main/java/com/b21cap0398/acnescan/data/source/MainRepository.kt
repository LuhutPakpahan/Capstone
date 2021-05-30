package com.b21cap0398.acnescan.data.source

import androidx.lifecycle.LiveData
import com.b21cap0398.acnescan.data.source.firebase.FirebaseDataSource
import com.b21cap0398.acnescan.data.source.local.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainRepository(private val firebaseDataSource: FirebaseDataSource): MainDataSource{

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(firebaseDataSource: FirebaseDataSource): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(firebaseDataSource)
            }
    }

    override fun getAcneScanResult(email: String, pos: Int): LiveData<AcneScanResult> {
        TODO("Not yet implemented")
    }

    override fun getAcneInformationById(acneId: String): LiveData<AcneInformation> {
        TODO("Not yet implemented")
    }

    override fun getUserInformation(email: String): LiveData<UserInformation> {
        TODO("Not yet implemented")
    }

    override fun setUserInformation(email: String) {
        TODO("Not yet implemented")
    }

    override fun getMostCommonAcnes(): LiveData<List<CommonAcne>> {
        TODO("Not yet implemented")
    }

    override fun getDailyRead(): LiveData<List<Article>> {
        TODO("Not yet implemented")
    }

    override fun getAllAcneScanResult(email: String): LiveData<List<AcneScanResult>> {
        TODO("Not yet implemented")
    }


}