package com.example.newsfeedtask.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsItemNetworkEntity(
    @SerializedName("apiUrl")
    @Expose
    val apiUrl: String,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("isHosted")
    @Expose
    val isHosted: Boolean,
    @SerializedName("pillarId")
    @Expose
    val pillarId: String,
    @SerializedName("pillarName")
    @Expose
    val pillarName: String,
    @SerializedName("sectionId")
    @Expose
    val sectionId: String,
    @SerializedName("sectionName")
    @Expose
    val sectionName: String,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("webPublicationDate")
    @Expose
    val webPublicationDate: String,
    @SerializedName("webTitle")
    @Expose
    val webTitle: String,
    @SerializedName("webUrl")
    @Expose
    val webUrl: String,
    @SerializedName("fields")
    @Expose
    val fieldsNetworkEntity:FieldsNetwrokEntity,

    )