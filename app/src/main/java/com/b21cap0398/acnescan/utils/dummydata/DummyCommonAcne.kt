package com.b21cap0398.acnescan.utils.dummydata

import com.b21cap0398.acnescan.data.source.local.entity.CommonAcne

object DummyCommonAcne {
    fun addDummyAcnes(): List<CommonAcne> {
        val listCommonAcne = ArrayList<CommonAcne>()
        listCommonAcne.add(CommonAcne("acnes/Nodule/images/n1.JPG", "nodules",
        "An acne nodule looks like a small bump under your skin, nodular acne is considered a more severe form of acne. Unlike other types of acne, such as blackheads, acne nodules can’t be cleared up with over-the-counter (OTC) products alone."))
        listCommonAcne.add(CommonAcne("acnes/Blackhead/images/b12.jpg", "blackhead",
        "Blackheads are small bumps that appear on your skin due to clogged hair follicles. These bumps are called blackheads because the surface looks dark or black. Blackheads are a mild type of acne that usually form on the face, but they can also appear on the back, chest, neck , arms, and shoulders"))
        listCommonAcne.add(CommonAcne("acnes/Whitehead/images/w56.jpg", "whitehead",
        "A whitehead is a type of acne that forms when dead skin cells, oil, and bacteria become trapped within one of your pores. Whiteheads can be annoying, and they may seem to develop at the worst times. The good news is that whiteheads can be prevented with a combination of lifestyle changes and medical treatments."))
        listCommonAcne.add(CommonAcne("acnes/Pustule/images/p11.jpg", "pustula",
        "Pustules are small bumps on the skin that contain fluid or pus. They usually appear as white bumps surrounded by red skin. These bumps look very similar to pimples, but they can grow quite big. Pustules may develop on any part of the body, but they most commonly form on the back, chest, and face. They may be found in clusters on the same area of the body. Pustules may be a form of acne typically caused by hormonal imbalances or hormonal changes in the body. This is a very common skin condition, particularly among teenagers and young adults."))
        listCommonAcne.add(CommonAcne("acnes/Papule/images/pa23.JPG", "papula",
        "A papule is a small red bump. Its diameter is usually less than 5 millimeters  Papules don’t have a yellow or white center of pus. When a papule does accumulate pus, it becomes a pustule. Most papules become pustules. This process commonly takes a few days. While tempting, it’s recommended to not pop pustules. Doing so can risk bacteria spreading further as well as scarring."))
        return listCommonAcne
    }
}