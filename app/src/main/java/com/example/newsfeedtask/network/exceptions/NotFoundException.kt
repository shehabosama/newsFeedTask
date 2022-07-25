package com.example.newsfeedtask.network.exceptions

class NotFoundException(
    val code: String,
    override val message: String,
) : Exception(message)