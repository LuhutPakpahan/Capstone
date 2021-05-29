package com.b21cap0398.acnescan.utils

import com.b21cap0398.acnescan.data.source.local.entity.OtherPossibility

object DummyOtherPossibilities {
    fun getDummyOtherPossibilites() : List<OtherPossibility> {
        return arrayListOf(
            OtherPossibility("Acne Name", "10", "This is the description of acne 1"),
            OtherPossibility("Acne Name", "20", "This is the description of acne 2"),
            OtherPossibility("Acne Name", "30", "This is the description of acne 3"),
            OtherPossibility("Acne Name", "40", "This is the description of acne 4"),
            OtherPossibility("Acne Name", "50", "This is the description of acne 5"),
            OtherPossibility("Acne Name", "60", "This is the description of acne 6"),
            OtherPossibility("Acne Name", "70", "This is the description of acne 7"),
            OtherPossibility("Acne Name", "80", "This is the description of acne 8")
        )
    }
}