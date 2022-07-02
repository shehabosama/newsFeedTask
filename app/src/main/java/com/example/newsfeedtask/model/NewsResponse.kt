package com.example.newsfeedtask.model

data class NewsResponse(
    val currentPage: Int,
    val orderBy: String,
    val pageSize: Int,
    val pages: Int,
    val newsItems: List<NewsItem>,
    val startIndex: Int,
    val status: String,
    val total: Int,
    val userTier: String

)