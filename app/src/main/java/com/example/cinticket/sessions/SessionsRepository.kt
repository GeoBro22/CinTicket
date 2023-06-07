package com.example.cinticket.sessions

import com.example.cinticket.entities.Session
import com.example.cinticket.sessions.room.entities.SessionIdAndPriceTuple

interface SessionsRepository {
    suspend fun getSessionsForMovie(movieId:Long):List<Session>?
    suspend fun getDatesForMovie(movieId:Long):List<String>?
    suspend fun getTimesForMovie(movieId:Long,date:String):List<String>?
    suspend fun getSessionId(movieId: Long,date:String,time:String): SessionIdAndPriceTuple?
}