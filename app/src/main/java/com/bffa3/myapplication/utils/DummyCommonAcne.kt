package com.bffa3.myapplication.utils

import com.bffa3.myapplication.data.entity.CommonAcne

object DummyCommonAcne {
    fun addDummyAcnes(): List<CommonAcne> {
        val listCommonAcne = ArrayList<CommonAcne>()
        listCommonAcne.add(CommonAcne("1", 1))
        listCommonAcne.add(CommonAcne("2", 2))
        listCommonAcne.add(CommonAcne("3", 3))
        listCommonAcne.add(CommonAcne("4", 4))
        listCommonAcne.add(CommonAcne("5", 5))
        listCommonAcne.add(CommonAcne("6", 6))
        listCommonAcne.add(CommonAcne("7", 7))
        listCommonAcne.add(CommonAcne("8", 8))
        listCommonAcne.add(CommonAcne("9", 9))
        listCommonAcne.add(CommonAcne("10", 10))

        return listCommonAcne
    }
}