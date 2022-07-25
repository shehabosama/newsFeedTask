package com.example.newsfeedtask.network.model


//@Serializable
data class DefaultErrorResponse(
    var status: Int? = null,
    var error: String? = null,
    var detail: String? = null
)