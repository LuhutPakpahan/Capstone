package com.b21cap0398.acnescan.data.source

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.b21cap0398.acnescan.data.source.local.entity.*

interface MainDataSource {

    fun getAcneScanResult(email: String, result_id: String): LiveData<AcneScanResult>

    fun getAllPossibilites(email: String, result_id: String): LiveData<ArrayList<Possibility>>

    fun getAcneInformationById(acneId: String): LiveData<AcneInformation>

    fun getUserInformation(email: String): LiveData<UserInformation>

    fun setUserInformation(
        email: String,
        firstName: String,
        lastName: String,
        age: Long,
        gender: String
    )

    fun getMostCommonAcnes(): LiveData<List<CommonAcne>>

    fun getDailyRead(): LiveData<List<Article>>

    fun getAllAcceptedAcneScanResult(email: String): LiveData<List<AcneScanResult>>

    fun getAllPendingAcneScanResult(email: String): LiveData<List<AcneScanResult>>

    fun getAllRejectedAcneScanResult(email: String): LiveData<List<AcneScanResult>>

    fun setResultPhoto(bitmap: Bitmap, email: String, result_id: String): LiveData<String>

    fun setScanResultAndPossibilites(email: String, result_id: String, acneScanResult: AcneScanResult, possibilities: List<Possibility>)

    fun setFeedback(feedbackForm: FeedbackForm)

    fun getArticles(): LiveData<List<Article>>
}