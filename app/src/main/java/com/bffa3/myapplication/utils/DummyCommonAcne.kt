package com.bffa3.myapplication.utils

import com.bffa3.myapplication.data.entity.CommonAcne

object DummyCommonAcne {
    fun addDummyAcnes(): List<CommonAcne> {
        val listCommonAcne = ArrayList<CommonAcne>()
        listCommonAcne.add(CommonAcne("1", 1))
        listCommonAcne.add(CommonAcne("2", 2))
        listCommonAcne.add(CommonAcne("3", 3))
        listCommonAcne.add(CommonAcne("4", 4))
        return listCommonAcne
    }
}