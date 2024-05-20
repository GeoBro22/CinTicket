package com.example.cinticket.retrofit

import com.example.cinticket.retrofit.models.Movies
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiTest {
    @Headers("Content-Type: application/json")
    @GET("v1.3/movie")
    suspend fun searchMovies(
        @Header("X-API-KEY") token: String,
        @Query("selectFields") selectFields: List<String>,  // Здесь указываем поля, которые хотим получить
        @Query("ticketsOnSale") ticketsOnSale: Boolean
    ) : Movies

}