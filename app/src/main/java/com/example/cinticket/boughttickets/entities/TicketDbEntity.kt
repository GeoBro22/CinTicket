package com.example.cinticket.boughttickets.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinticket.entities.Ticket

@Entity(
    tableName = "tickets",
)
data class TicketDbEntity(
    @PrimaryKey(autoGenerate = true) val ticketId: Long,
    val sessionId:Long,
    val placeId:Long,
) {

    fun toTicket(): Ticket = Ticket(
        ticketId=ticketId,
        sessionId=sessionId,
        placeId=placeId
    )
}