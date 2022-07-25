package com.example.newsfeedtask.network.exceptions

class UnauthorizedException(
    val code: String,
    override val message: String,
) : Exception(message)