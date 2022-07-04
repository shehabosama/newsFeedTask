package com.example.newsfeedtask.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FieldsNetwrokEntity(
    @SerializedName("thumbnail")
    @Expose
    val thumbnail:String
) {
}