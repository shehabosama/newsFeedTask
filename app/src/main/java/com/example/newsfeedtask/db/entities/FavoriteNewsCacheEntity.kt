package com.example.newsfeedtask.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_news")
data class FavoriteNewsCacheEntity(
    @ColumnInfo(name = "apiUrl")
    val apiUrl: String = "",
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String = "",
    @ColumnInfo(name = "isHosted")
    val isHosted: Boolean = false,
    @ColumnInfo(name = "pillarId")
    val pillarId: String = "",
    @ColumnInfo(name = "pillarName")
    val pillarName: String = "",
    @ColumnInfo(name = "sectionId")
    val sectionId: String="",
    @ColumnInfo(name = "sectionName")
    val sectionName: String="",
    @ColumnInfo(name = "type")
    val type: String="",
    @ColumnInfo(name = "webPublicationDate")
    val webPublicationDate: String="",
    @ColumnInfo(name = "webTitle")
    val webTitle: String="",
    @ColumnInfo(name = "webUrl")
    val webUrl: String="",
    @ColumnInfo(name = "thumbnail")
    val fieldsCacheEntity: FieldsCacheEntity,
) {

}