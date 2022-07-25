package com.example.newsfeedtask.network.preferences

import com.example.newsfeedtask.network.configs.ConfigService
import javax.inject.Inject

class NetworkPreferences @Inject constructor(
    private val configService: ConfigService
) {
    fun getToken(): String = configService.getAccessToken()

}