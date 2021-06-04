package com.b21cap0398.acnescan.data.source.local.entity

data class AcneInformation(
    val listImagePaths: List<String>?,
    val causes: String?,
    val description: String?,
    val tips: String?,
    val product_names: List<String>?,
    val product_images: List<String>?,
    val product_prices: List<String>?
)
