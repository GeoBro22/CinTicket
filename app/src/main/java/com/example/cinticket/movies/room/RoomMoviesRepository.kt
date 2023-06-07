package com.example.cinticket.movies.room

import com.example.cinticket.entities.Movie
import com.example.cinticket.movies.MoviesRepository

class RoomMoviesRepository(private val moviesDao:MoviesDao): MoviesRepository {

    override suspend fun getTomorrowMovies(): List<Movie> {
        return moviesDao.getAllNow()!!.map { movieDbEntity -> movieDbEntity.toMovie() }
    }
    override suspend fun getSoonMovies(): List<Movie> {
        return moviesDao.getAllSoon()!!.map { movieDbEntity -> movieDbEntity.toMovie() }
    }
    override suspend fun getMovieOfGenre(genre: String): List<Movie>? {
        return moviesDao.getMovieOfGenre(genre)!!.map { movieDbEntity -> movieDbEntity.toMovie() }
    }

    override suspend fun getGenres(): List<String>? {
        return moviesDao.getGenres()
    }
}