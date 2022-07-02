package com.example.newsfeedtask.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsfeedtask.db.entities.FavoriteNewsCacheEntity


@Dao
interface FavoriteDao {
    @Insert
    suspend fun insertNewsFeedItem(FavoriteNewsCacheEntity: FavoriteNewsCacheEntity):Long

    @Query("delete from fav_news where id=:id")
    suspend fun deleteFavNews(id: String?)

    @Query("select * from fav_news")
    suspend fun getFavNewItemEntities():List<FavoriteNewsCacheEntity>
}