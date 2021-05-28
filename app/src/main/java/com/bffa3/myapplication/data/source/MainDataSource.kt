package com.bffa3.myapplication.data.source

interface MainDataSource {

    fun getResultsAcneScan() {}

    fun uploadAcneImage() {}

    fun getDailyArticleAcne() {}

    fun getMostCommonAcne() {}

    fun getUserProfile() {}

    fun setUserProfile() {}
}