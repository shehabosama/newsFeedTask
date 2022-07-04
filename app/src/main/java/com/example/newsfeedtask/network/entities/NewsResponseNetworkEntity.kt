package com.example.newsfeedtask.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class NewsResponseNetworkEntity(
    @SerializedName("currentPage")
    @Expose
    val currentPage: Int,
    @SerializedName("orderBy")
    @Expose
    val orderBy: String,
    @SerializedName("pageSize")
    @Expose
    val pageSize: Int,
    @SerializedName("pages")
    @Expose
    val pages: Int,
    @SerializedName("results")
    @Expose
    val newsItemNetworkEntities: List<NewsItemNetworkEntity>,
    @SerializedName("startIndex")
    @Expose
    val startIndex: Int,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("total")
    @Expose
    val total: Int,
    @SerializedName("userTier")
    @Expose
    val userTier: String,


)