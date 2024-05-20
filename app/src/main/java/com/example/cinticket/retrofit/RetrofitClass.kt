package com.example.cinticket.retrofit

import android.util.Log
import com.example.cinticket.movies.room.entities.MovieDbEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClass {
    val token = "WCM1AMH-G2JMSG5-Q2E5BT3-635STV6"
    val apiService = Retrofit.Builder()
        .baseUrl("https://api.kinopoisk.dev")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiTest::class.java)


    suspend fun getMoviesFromApi(): MutableList<MovieDbEntity> {
        val selectFields = listOf(
            "name",
            "genres.name",
            "videos.trailers.url",
            "poster.url",
            "backdrop.url",
            "description",
            "movieLength"
        )
        val response = apiService.searchMovies(
            token = token,
            selectFields = selectFields,
            ticketsOnSale = true
        )
        Log.d("AAA", response.toString())
        val movies: MutableList<MovieDbEntity> = mutableListOf()
        var isnow = true
        for (i in 0 until 8) {
            if (i % 2 == 0)
                isnow = true
            else
                isnow = false
            val id = (i + 1).toLong()
            val moviename = response.docs[i].name

            val genre = response.docs[i].genres[0].name

            val description = response.docs[i].description
            val youtube: String

            if (response.docs[i].videos == null || response.docs[i].videos.trailers.isEmpty())
                youtube = "sdsasda"
            else
                youtube =
                    Regex("/([^/]+)$").find(response.docs[i].videos.trailers[0].url)!!.groupValues[1]
            val image = response.docs[i].poster.url
            val poster = response.docs[i].backdrop.url
            val movielong = response.docs[i].movieLength.toString() + " мин."
            val movie = MovieDbEntity(
                id = id,
                moviename = moviename,
                genre = genre,
                description = description,
                youtube = youtube,
                image = image,
                poster = poster,
                movielong = movielong,
                isnow = isnow
            )
            movies.add(movie)
        }
        return movies
    }
}