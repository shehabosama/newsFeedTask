package com.example.newsfeedtask.di

import android.content.Context
import com.example.newsfeedtask.network.configs.ConfigService
import com.example.newsfeedtask.network.configs.ConfigServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ConfigModule {

    @Provides
    @Singleton
    fun providePrefsService(@ApplicationContext context: Context): ConfigService {
        return ConfigServiceImpl(context, "configurations")
    }
}