package com.news2day.webviewtest.network.repos

import com.news2day.webviewtest.network.models.CatResposeData
import com.news2day.webviewtest.network.ApiService
import com.news2day.webviewtest.network.SafeApiRequest
import retrofit2.Response

class DataRepository(private val apiService : ApiService) : SafeApiRequest() {
    suspend fun getCatData(): Response<CatResposeData> {
        return apiService.getCatList()
    }

    suspend fun getCatDataResponse() : CatResposeData {
       return apiRequest {
            apiService.getCatList()
        }
    }
}

//response.body()?.data.also {
//    categoryLiveData.value = it?.let { it1 -> ArrayList(it1) }
//}