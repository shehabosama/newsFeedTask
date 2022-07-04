package com.example.newsfeedtask.db

import androidx.room.TypeConverter
import com.example.newsfeedtask.db.entities.FieldsCacheEntity
import org.json.JSONObject

class FieldConverter {
    @TypeConverter
    fun fromField(fieldCacheEntity:FieldsCacheEntity):String{
        return JSONObject().apply {
            put("thumbnail" , fieldCacheEntity.thumbnail)
        }.toString()
    }
    @TypeConverter
    fun toField(fieldString :String):FieldsCacheEntity{
        val json = JSONObject(fieldString)
        return FieldsCacheEntity(json.getString("thumbnail"))
    }
}