package com.example.newsfeedtask.network

import com.example.newsfeedtask.network.entities.MainResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitAPI {
    @GET("search")
    suspend fun getNewsData(@Query("page") page: Int,
                            @Query("api-key") api_key: String = "test"): MainResponse
}