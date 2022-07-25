package com.example.newsfeedtask.network.model

import com.example.newsfeedtask.util.DataState
import com.google.gson.Gson
import retrofit2.Response



abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataState<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return DataState.Success(body)
                }
            } else if (response.errorBody() != null) {
                val errorResponse = Gson().fromJson(response.errorBody()?.string(), APIError::class.java)

                // Simply returning api's own failure message
                return DataState.Error(exception = Exception(errorResponse?.message() ?: "Something went wrong"))
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): DataState<T> =
        DataState.Error(Exception("Api call failed $errorMessage"))
}
