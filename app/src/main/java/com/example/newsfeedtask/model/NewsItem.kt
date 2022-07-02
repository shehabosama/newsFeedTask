package com.example.newsfeedtask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
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
    val webUrl: String=""
):Serializable