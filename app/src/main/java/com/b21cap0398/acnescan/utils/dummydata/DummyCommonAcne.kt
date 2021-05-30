package com.b21cap0398.acnescan.utils.dummydata

import com.b21cap0398.acnescan.data.source.local.entity.CommonAcne

object DummyCommonAcne {
    fun addDummyAcnes(): List<CommonAcne> {
        val listCommonAcne = ArrayList<CommonAcne>()
        listCommonAcne.add(CommonAcne("1", "acne name"))
        listCommonAcne.add(CommonAcne("2", "acne name"))
        listCommonAcne.add(CommonAcne("3", "acne name"))
        listCommonAcne.add(CommonAcne("4", "acne name"))
        listCommonAcne.add(CommonAcne("5", "acne name"))
        listCommonAcne.add(CommonAcne("6", "acne name"))
        listCommonAcne.add(CommonAcne("7", "acne name"))
        listCommonAcne.add(CommonAcne("8", "acne name"))
        listCommonAcne.add(CommonAcne("9", "acne name"))
        listCommonAcne.add(CommonAcne("10", "acne name"))

        return listCommonAcne
    }
}