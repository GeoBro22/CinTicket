package com.example.cinticket

import android.app.Application
import androidx.room.Room
import com.example.cinticket.accounts.room.AccountsRepositoryRoomImpl
import com.example.cinticket.boughttickets.room.TicketsRepositoryRoomImpl
import com.example.cinticket.movies.room.RoomMoviesRepository
import com.example.cinticket.places.room.PlacesRepositoryRoomImpl
import com.example.cinticket.retrofit.RetrofitClass
import com.example.cinticket.room.AppDatabase
import com.example.cinticket.sessions.room.SessionsRepositoryRoomImpl
import com.example.cinticket.sharedpreferences.SharedPrefs

class App : Application() {

    companion object{
        const val FROM="CinTicket"
    }
    lateinit var service: Service
    private lateinit var db: AppDatabase
    lateinit var sharedPreferences: SharedPrefs
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "Cinema.db"
        )
            .createFromAsset("CinTicketDB.db")//.fallbackToDestructiveMigration()
            .build()
        val repositoryMovies = RoomMoviesRepository(db.getMoviesDao())
        val repositorySessions = SessionsRepositoryRoomImpl(db.getSessionsDao())
        val repositoryAccounts= AccountsRepositoryRoomImpl(db.getAccountsDao())
        val repositoryPlaces = PlacesRepositoryRoomImpl(db.getPlacesDao())
        val repositoryTickets = TicketsRepositoryRoomImpl(db.getTicketsDao())
        val retrofitRepository = RetrofitClass()
        sharedPreferences=SharedPrefs(applicationContext)
        service = Service(repositoryMovies,repositoryAccounts,repositorySessions,repositoryPlaces,repositoryTickets,retrofitRepository)
    }

}