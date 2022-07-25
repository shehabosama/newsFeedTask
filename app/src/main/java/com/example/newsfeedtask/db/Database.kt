package com.example.newsfeedtask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsfeedtask.db.daos.FavoriteDao
import com.example.newsfeedtask.db.daos.NewsItemDao
import com.example.newsfeedtask.db.entities.FavoriteNewsCacheEntity
import com.example.newsfeedtask.db.entities.NewsItemCacheEntity

@Database(entities = [ NewsItemCacheEntity::class, FavoriteNewsCacheEntity::class] , version = 2)
@TypeConverters(FieldConverter::class)
abstract class Database :RoomDatabase(){
    abstract fun newsItemDao(): NewsItemDao
    abstract fun favoriteDao(): FavoriteDao
    companion object{
        val DATABASE_NAME:String = "blog_db"
    }
}