package com.example.cinticket.movies

import com.example.cinticket.entities.Movie

interface MoviesRepository {
    suspend fun getTomorrowMovies(): List<Movie?>
    suspend fun getSoonMovies(): List<Movie?>
    suspend fun getMovieOfGenre(genre: String): List<Movie>?
    suspend fun getGenres(): List<String>?
}