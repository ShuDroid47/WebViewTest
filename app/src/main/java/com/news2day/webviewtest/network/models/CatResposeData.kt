package com.news2day.webviewtest.network.models

data class CatResposeData (

    val data: List<CategoryData>,
    val status: Int,
    val success: String
)