package com.example.newsfeedtask.di

import com.example.newsfeedtask.network.configs.ConfigService
import com.example.newsfeedtask.network.interceptors.TokenInterceptor
import com.example.newsfeedtask.network.preferences.NetworkPreferences
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
    fun provideTokenInterceptor(networkPreferences: NetworkPreferences): TokenInterceptor {
        return TokenInterceptor(networkPreferences)
    }


}
