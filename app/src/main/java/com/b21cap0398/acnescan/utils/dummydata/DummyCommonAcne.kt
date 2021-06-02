package com.b21cap0398.acnescan.utils.dummydata

import com.b21cap0398.acnescan.data.source.local.entity.CommonAcne

object DummyCommonAcne {
    fun addDummyAcnes(): List<CommonAcne> {
        val listCommonAcne = ArrayList<CommonAcne>()
        listCommonAcne.add(CommonAcne("acnes/Nodule/images/n1.JPG", "nodules"))
        listCommonAcne.add(CommonAcne("acnes/Blackhead/images/b12.jpg", "blackhead"))
        listCommonAcne.add(CommonAcne("acnes/Whitehead/images/w56.jpg", "whitehead"))
        listCommonAcne.add(CommonAcne("acnes/Pustule/images/p11.jpg", "pustula"))
        listCommonAcne.add(CommonAcne("acnes/Papule/images/pa23.JPG", "papula"))
        return listCommonAcne
    }
}