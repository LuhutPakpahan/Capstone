package com.b21cap0398.acnescan.data.source

import androidx.lifecycle.LiveData
import com.b21cap0398.acnescan.data.source.local.entity.*

interface MainDataSource {

    fun getAcneScanResult(email: String, result_id: String) : LiveData<AcneScanResult>

    fun getAllPossibilites(email: String, result_id: String) : LiveData<List<Possibility>>

    fun getAcneInformationById(acneId: String) : LiveData<AcneInformation>

    fun getUserInformation(email: String) : LiveData<UserInformation>

    fun setUserInformation(email: String)

    fun getMostCommonAcnes() : LiveData<List<CommonAcne>>

    fun getDailyRead() : LiveData<List<Article>>

    fun getAllAcceptedAcneScanResult(email: String) : LiveData<List<AcneScanResult>>

    fun getAllPendingAcneScanResult(email: String) : LiveData<List<AcneScanResult>>

    fun getAllRejectedAcneScanResult(email: String) : LiveData<List<AcneScanResult>>
}