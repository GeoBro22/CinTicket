package com.example.cinticket.movies.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinticket.entities.Movie

@Entity(
    tableName = "movies"
)

data class MovieDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val moviename: String,
    val genre: String,
    val youtube: String,
    val image: String,
    val poster: String,
    val description: String,
    val movielong: String,
    val isnow:Boolean
) {

    fun toMovie(): Movie = Movie(
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