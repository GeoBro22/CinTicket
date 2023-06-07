package com.example.cinticket.sessions.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinticket.entities.Session

@Entity(
    tableName = "sessions"
)

data class SessionDbEntity(
    @PrimaryKey(autoGenerate = true) val sessionId: Long,
    val date:String,
    val time:String,
    val movieId:Long,
    val price:Long
) {

    fun toSession(): Session = Session(
        sessionId=sessionId,
        date = date,
        time = time,
        movieId = movieId,
        price = price
    )
}