package com.b21cap0398.acnescan.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.b21cap0398.acnescan.data.source.firebase.FirebaseDataSource
import com.b21cap0398.acnescan.data.source.local.entity.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
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

    override fun getAcneScanResult(email: String, result_id: String): LiveData<AcneScanResult> {
        val result = MutableLiveData<AcneScanResult>()
        var test = "0"
        CoroutineScope(IO).launch {
            firebaseDataSource.getAcneScanResult(email, result_id, object : FirebaseDataSource.LoadAcneScanResultCallback {
                override fun onAcneScanResultReceived(acneScanResult: AcneScanResult) {
                    Log.d("tag", acneScanResult.toString())
                }
            })
        }
        return result
    }

    override fun getAllPossibilites(email: String, result_id: String): LiveData<List<Possibility>> {
        val result = MutableLiveData<List<Possibility>>()
        CoroutineScope(IO).launch {
            firebaseDataSource.getAllPossibilities(email, result_id,
                object : FirebaseDataSource.LoadAllPossibilitiesCallback {
                    override fun onAllPosibilitiesReceived(possibilities: List<Possibility>) {
                        result.postValue(possibilities)
                    }

                })
        }

        return result
    }

    override fun getAcneInformationById(acneId: String): LiveData<AcneInformation> {
        val result = MutableLiveData<AcneInformation>()
        CoroutineScope(IO).launch {
            firebaseDataSource.getAcneInformationById(acneId,
                object : FirebaseDataSource.LoadAcneInformationCallback {
                    override fun onAcneInformationReceived(acneInformation: AcneInformation) {
                        result.postValue(acneInformation)
                    }
                })
        }

        return result
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

    override fun getAllAcceptedAcneScanResult(email: String): LiveData<List<AcneScanResult>> {
        val result = MutableLiveData<List<AcneScanResult>>()
        CoroutineScope(IO).launch {
            firebaseDataSource.getAllAcceptedAcneScanResult(email,
                object : FirebaseDataSource.LoadAllAcceptedAcneScanResultCallback {
                    override fun onAllAcceptedAcneScanResultReceived(acneScanResults: List<AcneScanResult>) {
                        result.postValue(acneScanResults)
                    }

                })
        }

        return result
    }

    override fun getAllPendingAcneScanResult(email: String): LiveData<List<AcneScanResult>> {
        TODO("Not yet implemented")
    }

    override fun getAllRejectedAcneScanResult(email: String): LiveData<List<AcneScanResult>> {
        TODO("Not yet implemented")
    }
}