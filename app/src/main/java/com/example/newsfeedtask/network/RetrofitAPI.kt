package com.example.newsfeedtask.network

import com.example.newsfeedtask.network.entities.MainResponse
import com.example.newsfeedtask.network.entities.ResponseStatus
import okhttp3.MultipartBody
import retrofit2.http.*

interface RetrofitAPI {
    @GET("search")
    suspend fun getNewsData(@Query("page") page: Int,
                            @Query("api-key") api_key: String = "test",
                            @Query("show-fields")show_fields:String="thumbnail"): MainResponse

    @Multipart
    @POST("/upload_file.php")
    suspend fun uploadFile(
        @Part imageUri: MultipartBody.Part
    ): ResponseStatus
}