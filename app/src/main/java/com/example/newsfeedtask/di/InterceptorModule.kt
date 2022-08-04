package com.example.newsfeedtask.di

import com.example.newsfeedtask.network.configs.ConfigService
import com.example.newsfeedtask.network.interceptors.HttpInterceptor
import com.example.newsfeedtask.network.interceptors.TokenInterceptor
import com.example.newsfeedtask.network.model.ErrorResponse
import com.example.newsfeedtask.network.preferences.NetworkPreferences
import com.google.gson.Gson
import com.google.gson.annotations.JsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class InterceptorModule {

    @Provides
    @Singleton
    fun provideNetworkPreferences(configService: ConfigService): NetworkPreferences {
        return NetworkPreferences(configService)
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(networkPreferences: NetworkPreferences , gson: Gson): TokenInterceptor {
        return TokenInterceptor(networkPreferences,gson)
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(gson: Gson): HttpInterceptor {
        return HttpInterceptor(gson)
    }
}
