package com.example.newsfeedtask.di

import android.content.Context
import androidx.room.Room

import com.example.newsfeedtask.db.Database
import com.example.newsfeedtask.db.daos.FavoriteDao
import com.example.newsfeedtask.db.daos.NewsItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provedBlogDb(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context , Database::class.java , Database.DATABASE_NAME )
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideNewsItemDAO(database: Database): NewsItemDao {
        return database.newsItemDao()
    }
    @Singleton
    @Provides
    fun provideFavNewsItemDAO(database: Database): FavoriteDao {
        return database.favoriteDao()
    }
}