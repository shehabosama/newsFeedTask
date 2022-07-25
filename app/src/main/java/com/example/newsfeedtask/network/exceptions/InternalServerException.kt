package com.example.newsfeedtask.network.exceptions

class InternalServerException(
    val code: String,
    override val message: String,
) : Exception(message)