package com.example.cinticket.boughttickets.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cinticket.boughttickets.entities.TicketDbEntity
import com.example.cinticket.boughttickets.entities.TicketInsertTuple

@Dao
interface TicketsDao {
    @Query("SELECT * FROM tickets WHERE sessionId = :sessionId")
    suspend fun getPlacesForSession(sessionId: Long) : List<TicketDbEntity>?

    @Query("SELECT placeID FROM tickets WHERE sessionId = :sessionId")
    suspend fun getSelledPlaces(sessionId: Long) : List<Long>?

    @Insert(entity = TicketDbEntity::class)
    suspend fun insertNewTicket(newTicket: TicketInsertTuple)
}