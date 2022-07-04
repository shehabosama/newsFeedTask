package com.example.newsfeedtask.model

import com.example.newsfeedtask.network.entities.FieldsNetwrokEntity
import java.io.Serializable

data class NewsItem(
    val apiUrl: String = "",
    val id: String = "",
    val isHosted: Boolean = false,
    val pillarId: String = "",
    val pillarName: String = "",
    val sectionId: String="",
    val sectionName: String="",
    val type: String="",
    val webPublicationDate: String="",
    val webTitle: String="",
    val webUrl: String="",
    val fields: Fields
):Serializable