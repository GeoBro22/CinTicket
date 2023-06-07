package com.example.cinticket.boughttickets

import com.example.cinticket.boughttickets.entities.TicketInsertTuple
import com.example.cinticket.entities.Ticket

interface TicketsRepository {
    suspend fun getPlacesForSession(sessionId: Long) : List<Ticket>?
    suspend fun getSelledPlaces(sessionId: Long) : List<Long>?
    suspend fun insertNewTicket(newTicket: TicketInsertTuple)
}