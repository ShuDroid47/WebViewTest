package com.news2day.webviewtest.network.repos

import com.news2day.webviewtest.constants.Constants
import com.news2day.webviewtest.models.CatResposeData
import com.news2day.webviewtest.network.ApiService
import retrofit2.Response

class DataRepository(private val apiService : ApiService) {
    suspend fun getCatData() : Response<CatResposeData> {
       return apiService.getCatList(Constants.headerToken)
    }
}

//response.body()?.data.also {
//    categoryLiveData.value = it?.let { it1 -> ArrayList(it1) }
//}