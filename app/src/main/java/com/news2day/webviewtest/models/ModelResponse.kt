package com.news2day.webviewtest.models

data class ModelResponse(
    val data: List<Data>,
    val status: Int,
    val success: String
)