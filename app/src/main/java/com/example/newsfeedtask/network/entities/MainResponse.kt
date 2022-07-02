package com.example.newsfeedtask.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("response")
    @Expose
    val response: NewsResponseNetworkEntity
)
