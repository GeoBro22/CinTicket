package com.example.cinticket.sessions.room

import com.example.cinticket.entities.Session
import com.example.cinticket.sessions.SessionsRepository
import com.example.cinticket.sessions.room.entities.SessionIdAndPriceTuple

class SessionsRepositoryRoomImpl(private val sessionsDao: SessionsDao) : SessionsRepository {
    override suspend fun getSessionsForMovie(movieId: Long): List<Session>? {
        return sessionsDao.getSessionsForMovie(movieId)!!
            .map { sessionDbEntity -> sessionDbEntity.toSession() }
    }

    override suspend fun getDatesForMovie(movieId: Long): List<String>? {
        return sessionsDao.getDatesForMovie(movieId)
    }

    override suspend fun getTimesForMovie(movieId: Long, date: String): List<String>? {
        return sessionsDao.getTimesForMovie(movieId,date)
    }

    override suspend fun getSessionId(movieId: Long, date: String, time: String): SessionIdAndPriceTuple? {
        return sessionsDao.getSessionId(movieId,date,time)
    }
}