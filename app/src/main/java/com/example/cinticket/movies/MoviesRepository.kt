package com.example.cinticket.movies

import com.example.cinticket.entities.Movie
import com.example.cinticket.movies.room.entities.MovieDbEntity

interface MoviesRepository {
    suspend fun getTomorrowMovies(): List<Movie?>
    suspend fun getSoonMovies(): List<Movie?>
    suspend fun getMovieOfGenre(genre: String): List<Movie>?
    suspend fun getGenres(): List<String>?
    suspend fun insertMovie(movieDbEntity: MovieDbEntity)
}