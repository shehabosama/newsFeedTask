package com.example.newsfeedtask.di


import android.content.Context
import com.example.newsfeedtask.network.interceptors.TokenInterceptor
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.newsfeedtask.BuildConfig
import com.example.newsfeedtask.network.RetrofitAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    /**
    WE COMMENTED IT BECAUSE WE ALREADY Inject IT THERE IN ITS CLASS
    @Singleton
    @Provides
    fun ProvideNetworkMapper():EntityMapper<BlogNetworkEntity , Blog>{
        return NetworkMapper()
    }
    */


    // Gson object responsible for parsing gson data to java objects
    @Singleton
    @Provides
    fun provideGsonBuilder():Gson{
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        // "excludeFieldsWithoutExposeAnnotation()" if there is properties with out @Expose Annotation will ignore it
    }

    @Singleton
    @Provides
    fun provideRetrofit1(gson: Gson , okHttpClient: OkHttpClient):Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://content.guardianapis.com/")
            .addConverterFactory(/*this for converting gson to java object*/GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    }

    @Singleton
    @Provides
    fun provideAPIService1(retrofit: Retrofit): RetrofitAPI {
        return retrofit.create(RetrofitAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context?, tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(ChuckerInterceptor(context!!))
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        okHttpClientBuilder.addInterceptor(tokenInterceptor)
        return okHttpClientBuilder
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .cache(null)
            .build()
    }


}