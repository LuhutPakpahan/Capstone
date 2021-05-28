package com.b21cap0398.acnescan.data.source

interface MainDataSource {

    fun getResultsAcneScan() {}

    fun uploadAcneImage() {}

    fun getDailyArticleAcne() {}

    fun getMostCommonAcne() {}

    fun getUserProfile() {}

    fun setUserProfile() {}
}