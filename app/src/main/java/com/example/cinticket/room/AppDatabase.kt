package com.example.cinticket.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinticket.accounts.entities.AccountDbEntity
import com.example.cinticket.accounts.room.AccountsDao
import com.example.cinticket.boughttickets.entities.TicketDbEntity
import com.example.cinticket.boughttickets.room.TicketsDao
import com.example.cinticket.movies.room.MoviesDao
import com.example.cinticket.movies.room.entities.MovieDbEntity
import com.example.cinticket.places.room.PlacesDao
import com.example.cinticket.places.room.entities.PlaceDbEntity
import com.example.cinticket.sessions.room.SessionsDao
import com.example.cinticket.sessions.room.entities.SessionDbEntity

@Database(
    version = 1,
    entities = [
        MovieDbEntity::class,
        SessionDbEntity::class,
        PlaceDbEntity::class,
        AccountDbEntity::class,
        TicketDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    abstract fun getSessionsDao(): SessionsDao

    abstract fun getPlacesDao(): PlacesDao

    abstract fun getAccountsDao(): AccountsDao

    abstract fun getTicketsDao(): TicketsDao

}