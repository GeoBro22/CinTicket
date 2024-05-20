package com.example.cinticket.movies.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cinticket.movies.room.entities.MovieDbEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies WHERE isnow = 1")
    suspend fun getAllNow() : List<MovieDbEntity>?

    @Query("SELECT * FROM movies WHERE isnow = 0")
    suspend fun getAllSoon() : List<MovieDbEntity>?

    @Query("SELECT * FROM movies WHERE genre = :genre AND isnow = 1")
    suspend fun getMovieOfGenre(genre: String): List<MovieDbEntity>?

    @Query("SELECT genre FROM movies WHERE isnow = 1")
    suspend fun getGenres(): List<String>?

    @Insert
    suspend fun insertMovie(movie: MovieDbEntity)
}