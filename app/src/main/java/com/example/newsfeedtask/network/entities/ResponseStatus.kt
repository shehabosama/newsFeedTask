package com.example.newsfeedtask.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ResponseStatus(
    @SerializedName("message")
    @Expose
    val message: String,
    @SerializedName("status")
    @Expose
    val status: Int
)