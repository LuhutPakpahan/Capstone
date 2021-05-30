package com.b21cap0398.acnescan.data.source.local.entity

import com.google.firebase.Timestamp

data class AcneScanResult(
    val imagePath: String?,
    val date: String?,
    val possibilities: List<Possibility>?
)
