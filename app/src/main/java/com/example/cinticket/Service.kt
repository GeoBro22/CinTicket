package com.example.cinticket

import com.example.cinticket.accounts.AccountsRepository
import com.example.cinticket.accounts.entities.AccountUpdateCardInfoTuple
import com.example.cinticket.boughttickets.TicketsRepository
import com.example.cinticket.boughttickets.entities.TicketInsertTuple
import com.example.cinticket.entities.Account
import com.example.cinticket.entities.Movie
import com.example.cinticket.entities.Session
import com.example.cinticket.entities.Ticket
import com.example.cinticket.movies.room.RoomMoviesRepository
import com.example.cinticket.places.PlacesRepository
import com.example.cinticket.sessions.SessionsRepository
import com.example.cinticket.sessions.room.entities.SessionIdAndPriceTuple

class Service(
    private val moviesRepository: RoomMoviesRepository,
    private val accountsRepository: AccountsRepository,
    private val sessionsRepository: SessionsRepository,
    private val placesRepository: PlacesRepository,
    private val ticketsRepository: TicketsRepository
) {
    private var tomorrowMovies = mutableListOf<Movie>()
    private var soonMovies = mutableListOf<Movie>()
    //init
    fun isValidEmail(email: String): Boolean {
        val regex = Regex("^[A-Za-z]+[A-Za-z0-9._-]*[A-Za-z0-9]*@gmail.com")
        return regex.matches(email)
    }
    fun isValidDate(date: String): Boolean {
        val regex = Regex("^[0-9]{2}/[0-9]{2}")
        return regex.matches(date)
    }


    suspend fun getTomorrowMovies(): MutableList<Movie> {
        tomorrowMovies = moviesRepository.getTomorrowMovies().toMutableList()
        return tomorrowMovies
    }

    suspend fun getSoonMovies(): List<Movie> {
        soonMovies = moviesRepository.getSoonMovies().toMutableList()
        return soonMovies
    }

    suspend fun getAccountByEmail(email: String): Account? {
        return accountsRepository.getAccountByEmail(email)
    }

    suspend fun insertNewAccount(account: Account) {
        accountsRepository.insertNewAccount(account.toAccountDbEntity())
    }

    suspend fun getSessionsForMovie(movieId: Long): List<Session>? {
        return sessionsRepository.getSessionsForMovie(movieId)?.toMutableList()
    }

    suspend fun getDatesForMovie(movieId: Long): Set<String>? {
        return sessionsRepository.getDatesForMovie(movieId)?.toSet()
    }

    suspend fun getTimesForMovie(movieId: Long, date: String): List<String>? {
        return sessionsRepository.getTimesForMovie(movieId, date)
    }

    suspend fun getPlacesForSession(sessionId: Long): List<Ticket>? {
        return ticketsRepository.getPlacesForSession(sessionId)?.toMutableList()
    }

    suspend fun getNumOfSeats(): List<Long>? {
        return placesRepository.getIds()
    }

    suspend fun getSelledPlaces(sessionId: Long): List<Long>? {
        return ticketsRepository.getSelledPlaces(sessionId)
    }

    suspend fun getSessionId(movieId: Long, date: String, time: String): SessionIdAndPriceTuple? {
        return sessionsRepository.getSessionId(movieId, date, time)
    }

    suspend fun updateAccountCardInfo(updatedAccountCardInfo: AccountUpdateCardInfoTuple){
        if (updatedAccountCardInfo.accountId!=0L)
            accountsRepository.updateAccountCardInfo(updatedAccountCardInfo)
    }
    suspend fun insertNewTicket(newTicket: TicketInsertTuple){
        ticketsRepository.insertNewTicket(newTicket)
    }
    suspend fun getMovieOfGenre(genre: String): List<Movie>?{
        return moviesRepository.getMovieOfGenre(genre)?.toMutableList()
    }
    suspend fun getGenres(): List<String>?{
        return moviesRepository.getGenres()?.toSet()?.toMutableList()
    }
}