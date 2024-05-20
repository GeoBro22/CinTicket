package com.example.cinticket.retrofit.models

data class Movie(
    val name: String,
    val genres: List<GenresName>,
    val videos: Trailers,
    val poster: PosterUrl,
    val backdrop: BackdropUrl,
    val description: String,
    val movieLength: Number
)

