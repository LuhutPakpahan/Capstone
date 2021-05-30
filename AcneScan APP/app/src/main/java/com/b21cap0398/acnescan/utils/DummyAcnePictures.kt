package com.b21cap0398.acnescan.utils

import com.b21cap0398.acnescan.data.source.local.entity.AcnePicture

object DummyAcnePictures {
    fun addDummyAcnePictures(): List<AcnePicture> {
        return listOf(
            AcnePicture("1", "1"),
            AcnePicture("2", "2"),
            AcnePicture("3", "3"),
            AcnePicture("4", "4"),
            AcnePicture("5", "5"),
            AcnePicture("6", "6"),
            AcnePicture("7", "7"),
            AcnePicture("8", "8"),
            AcnePicture("9", "9"),
            AcnePicture("10", "10")
        )
    }
}