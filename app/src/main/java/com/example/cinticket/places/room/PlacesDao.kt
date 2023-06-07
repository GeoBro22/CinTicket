package com.example.cinticket.places.room

import androidx.room.Dao
import androidx.room.Query
import com.example.cinticket.places.room.entities.PlaceDbEntity

@Dao
interface PlacesDao {

    @Query("SELECT * FROM places")
    suspend fun getAllPlaces() : List<PlaceDbEntity>?

    @Query("SELECT placeId FROM places")
    suspend fun getIds():List<Long>?
}