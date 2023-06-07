package com.example.cinticket.entities

data class Session(
    val sessionId: Long,
    val date: String,
    val time: String,
    val movieId: Long,
    val price:Long
)