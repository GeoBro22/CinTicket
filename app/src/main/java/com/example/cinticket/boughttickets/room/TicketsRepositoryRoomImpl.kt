package com.example.cinticket.boughttickets.room

import com.example.cinticket.boughttickets.TicketsRepository
import com.example.cinticket.boughttickets.entities.TicketInsertTuple
import com.example.cinticket.entities.Ticket

class TicketsRepositoryRoomImpl(private val ticketsDao: TicketsDao):TicketsRepository {
    override suspend fun getPlacesForSession(sessionId: Long): List<Ticket>? {
        return ticketsDao.getPlacesForSession(sessionId)?.map { ticketDbEntity -> ticketDbEntity.toTicket() }
    }

    override suspend fun getSelledPlaces(sessionId: Long): List<Long>? {
        return ticketsDao.getSelledPlaces(sessionId)
    }

    override suspend fun insertNewTicket(newTicket: TicketInsertTuple) {
        ticketsDao.insertNewTicket(newTicket)
    }

}