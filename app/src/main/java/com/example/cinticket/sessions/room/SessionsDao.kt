package com.example.cinticket.sessions.room

import androidx.room.Dao
import androidx.room.Query
import com.example.cinticket.sessions.room.entities.SessionDbEntity
import com.example.cinticket.sessions.room.entities.SessionIdAndPriceTuple

@Dao
interface SessionsDao {

    @Query("SELECT * FROM sessions WHERE movieId = :movieId")
    suspend fun getSessionsForMovie(movieId: Long): List<SessionDbEntity>?

    @Query("SELECT date FROM sessions WHERE movieId = :movieId")
    suspend fun getDatesForMovie(movieId: Long): List<String>?

    @Query("SELECT time FROM sessions WHERE movieId = :movieId AND date = :date")
    suspend fun getTimesForMovie(movieId: Long,date:String): List<String>?

    @Query("SELECT sessionId, price FROM sessions WHERE movieId = :movieId AND date = :date AND time = :time")
    suspend fun getSessionId(movieId: Long,date:String,time:String): SessionIdAndPriceTuple?
}