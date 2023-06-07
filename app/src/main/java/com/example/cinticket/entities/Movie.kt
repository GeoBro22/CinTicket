package com.example.cinticket.entities

data class Movie(
    val id: Long,
    val moviename: String,
    val genre: String,
    val youtube: String,
    val image: String,
    val poster: String,
    val description: String,
    val movielong: String,
    val isnow: Boolean
    )