package com.news2day.webviewtest.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.news2day.webviewtest.helpers.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isNetworkAvailable())
            throw NoInternetException("Not Connected to Internet.")
        return chain.proceed(chain.request())
    }

    private fun isNetworkAvailable() : Boolean {
        val connectivityManager = context.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        return connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)?:false
    }
}