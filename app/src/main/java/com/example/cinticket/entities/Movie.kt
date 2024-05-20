package com.example.cinticket.entities

import com.example.cinticket.movies.room.entities.MovieDbEntity

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
    ){
    fun toMovieDB(): MovieDbEntity = MovieDbEntity(
        id = id,
        moviename = moviename,
        genre = genre,
        youtube = youtube,
        image = image,
        poster = poster,
        description = description,
        movielong = movielong,
        isnow = isnow
    )
}


