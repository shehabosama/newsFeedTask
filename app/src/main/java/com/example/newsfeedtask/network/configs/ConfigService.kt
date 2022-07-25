package com.example.newsfeedtask.network.configs


interface ConfigService {
    fun setAccessToken(token: String?)
    fun getAccessToken(): String
    fun clearUserInfo()
}