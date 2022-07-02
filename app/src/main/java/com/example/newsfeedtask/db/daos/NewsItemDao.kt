package com.example.newsfeedtask.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsfeedtask.db.entities.NewsItemCacheEntity

@Dao
interface NewsItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlog(newsItemCacheEntity: NewsItemCacheEntity):Long

    @Query("SELECT * FROM result")
    suspend fun getNewsItemEntities():List<NewsItemCacheEntity>
}