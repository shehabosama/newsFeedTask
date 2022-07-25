package com.example.newsfeedtask.network.interceptors

import com.example.newsfeedtask.network.exceptions.InternalServerException
import com.example.newsfeedtask.network.exceptions.NotFoundException
import com.example.newsfeedtask.network.exceptions.UnauthorizedException
import com.example.newsfeedtask.network.model.APIError
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HttpInterceptor @Inject constructor(
    private val errorAdapter: Gson,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request()).apply {
            if (code !in 400..599) return@apply

            val errorDTO = body?.string()?.let { errorAdapter.fromJson(it , APIError::class.java) }
            val message = errorDTO?.message() ?: ""
            when (code) {
                401 -> throw UnauthorizedException(code.toString(), message)
                404 -> throw NotFoundException(code.toString(), message)
                in 500..599 -> throw InternalServerException(code.toString(), message)
            }
        }
}

// here could be any error body structure, for know except message
class ErrorDTO(
    val message: String?,
)