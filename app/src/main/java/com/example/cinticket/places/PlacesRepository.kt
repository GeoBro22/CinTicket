package com.example.cinticket.places

import com.example.cinticket.entities.Place

interface PlacesRepository {
    suspend fun getAllPlaces(): List<Place>?

    suspend fun getIds(): List<Long>?
}