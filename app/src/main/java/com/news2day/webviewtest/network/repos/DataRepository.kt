package com.news2day.webviewtest.network.repos

import com.news2day.webviewtest.constants.Constants
import com.news2day.webviewtest.network.ApiService

class DataRepository(private val apiService : ApiService) {

    fun getCatData() = apiService.getCatList(Constants.headerToken)
}