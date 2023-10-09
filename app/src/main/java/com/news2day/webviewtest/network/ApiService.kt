package com.news2day.webviewtest.network

import com.news2day.webviewtest.constants.Constants
import com.news2day.webviewtest.models.CatResposeData
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

public interface ApiService {

    @GET("user/get-footer-cat")
    suspend fun getCatList(@Header ("token") token : String) : Response<CatResposeData>

    companion object {
        operator fun invoke () : ApiService{
            return Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService :: class.java)
        }

    }
}