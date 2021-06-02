package com.b21cap0398.acnescan.utils.dummydata

import com.b21cap0398.acnescan.data.source.local.entity.CommonAcne

object DummyCommonAcne {
    fun addDummyAcnes(): List<CommonAcne> {
        val listCommonAcne = ArrayList<CommonAcne>()
        listCommonAcne.add(CommonAcne("1", "Nodules"))
        listCommonAcne.add(CommonAcne("2", "Black Head"))
        listCommonAcne.add(CommonAcne("3", "White Head"))
        listCommonAcne.add(CommonAcne("4", "Pustula"))
        listCommonAcne.add(CommonAcne("5", "Papula"))
        return listCommonAcne
    }
}