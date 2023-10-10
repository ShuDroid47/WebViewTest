package com.news2day.webviewtest.network

import com.news2day.webviewtest.constants.Constants
import com.news2day.webviewtest.network.models.CatResposeData
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("user/get-footer-cat")
    suspend fun getCatList() : Response<CatResposeData>

    companion object {
        operator fun invoke (
            connectionInterceptor: NetworkConnectionInterceptor
        ) : ApiService{

            val okHttpClient  = OkHttpClient.Builder()
                .addInterceptor(connectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService :: class.java)
        }
    }
}