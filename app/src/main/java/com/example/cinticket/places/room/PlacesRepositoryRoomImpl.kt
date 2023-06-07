package com.example.cinticket.places.room

import com.example.cinticket.entities.Place
import com.example.cinticket.places.PlacesRepository

class PlacesRepositoryRoomImpl(private val placesDao: PlacesDao):PlacesRepository {
    override suspend fun getAllPlaces(): List<Place>? {
        return placesDao.getAllPlaces()!!.map { placeDbEntity -> placeDbEntity.toPlace() }
    }

    override suspend fun getIds(): List<Long>? {
        return placesDao.getIds()
    }

}