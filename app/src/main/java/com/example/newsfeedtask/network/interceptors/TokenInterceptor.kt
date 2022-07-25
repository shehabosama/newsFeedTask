package com.example.newsfeedtask.network.interceptors

import com.example.newsfeedtask.network.preferences.NetworkPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val networkPreferences: NetworkPreferences,
) : Interceptor {

    companion object {
        private const val TOKEN_PREFIX = "Bearer"
        const val API_KEY: String = "nAdCsZsfxD2wRZFL"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            addHeader("bf-api-key", API_KEY)
            addHeader("authorization", "$TOKEN_PREFIX ${networkPreferences.getToken()}")
        }.build()
        return chain.proceed(request)
    }
}