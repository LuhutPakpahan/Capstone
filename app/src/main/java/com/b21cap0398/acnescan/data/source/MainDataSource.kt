package com.b21cap0398.acnescan.data.source

import androidx.lifecycle.LiveData
import com.b21cap0398.acnescan.data.source.local.entity.*

interface MainDataSource {

    fun getAcneScanResult(email: String, pos: Int) : LiveData<AcneScanResult>

    fun getAcneInformationById(acneId: String) : LiveData<AcneInformation>

    fun getUserInformation(email: String) : LiveData<UserInformation>

    fun setUserInformation(email: String)

    fun getMostCommonAcnes() : LiveData<List<CommonAcne>>

    fun getDailyRead() : LiveData<List<Article>>

    fun getAllAcneScanResult(email: String) : LiveData<List<AcneScanResult>>
}