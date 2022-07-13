package com.example.newsfeedtask.di

import android.content.Context

import com.example.newsfeedtask.R
import com.example.newsfeedtask.db.mapper.FavNewsItemCacheMapper
import com.example.newsfeedtask.db.daos.FavoriteDao
import com.example.newsfeedtask.db.mapper.NewsItemCacheMapper
import com.example.newsfeedtask.db.daos.NewsItemDao
import com.example.newsfeedtask.network.mapper.NewsItemNetworkMapper
import com.example.newsfeedtask.network.RetrofitAPI
import com.example.newsfeedtask.repository.FavNewsRepository
import com.example.newsfeedtask.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {



    @Singleton
    @Provides
    fun provideBlogRepository(newsItemDao: NewsItemDao,
                              retrofitAPI: RetrofitAPI,
                              blogCacheMapper: NewsItemCacheMapper,
                              newsItemNetworkMapper: NewsItemNetworkMapper
    ): NewsRepository {
        return NewsRepository(retrofitAPI ,newsItemDao , blogCacheMapper,newsItemNetworkMapper )
    }


    @Singleton
    @Provides
    fun provideFavNewsRepository(favoriteDao: FavoriteDao, favNewsItemCacheMapper: FavNewsItemCacheMapper): FavNewsRepository {
        return FavNewsRepository(favoriteDao,favNewsItemCacheMapper)
    }


    @Singleton
    @Provides
    fun getStringFromResources(@ApplicationContext context:Context):String = context.getString(
        R.string.app_name)
}