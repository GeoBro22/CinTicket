package com.example.cinticket.places.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinticket.entities.Place

@Entity(
    tableName = "places"
)

data class PlaceDbEntity(
    @PrimaryKey(autoGenerate = true) val placeId: Long,
    val placeNumber: Long,
    val rowNumber: Long,
) {

    fun toPlace(): Place = Place(
        placeId = placeId,
        placeNumber = placeNumber,
        rowNumber = rowNumber
    )
}