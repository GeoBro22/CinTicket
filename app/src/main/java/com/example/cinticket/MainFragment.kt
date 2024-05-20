package com.example.cinticket

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinticket.adapters.GenreAdapter
import com.example.cinticket.adapters.MovieAdapter
import com.example.cinticket.databinding.FragmentMainBinding
import com.example.cinticket.fragments.SettingsFragment
import com.example.cinticket.sharedpreferences.SharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UseCompatLoadingForDrawables")
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapterTomorrow: MovieAdapter
    private val service: Service
        get() = (context?.applicationContext as App).service
    private val sharedPreference: SharedPrefs
        get() = (context?.applicationContext as App).sharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val controller = findNavController()
        adapterTomorrow = MovieAdapter(controller)
        val adapterSoon = MovieAdapter(controller)
        val adapterGenre= GenreAdapter(controller,service,requireActivity())
        lifecycleScope.launch(Dispatchers.IO) {
            val movies = service.getMoviesFromApi()
            if (sharedPreference.getLong("movies_ready")==null) {
                for (movie in movies) {
                    sharedPreference.saveLong("movies_ready", 1)
                    service.insertMovie(movie)
                }
            }
            val tm = service.getTomorrowMovies()
            val sm = service.getSoonMovies()
            val genres=service.getGenres()
            launch(Dispatchers.Main) {
                adapterTomorrow.movies = tm
                adapterSoon.movies = sm
                if (genres != null) {
                    adapterGenre.genres = genres
                }
            }
        }

        val layoutManagerTomorrow =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerToday.layoutManager = layoutManagerTomorrow
        binding.recyclerToday.adapter = adapterTomorrow

        val layoutManagerSoon =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerFuture.layoutManager = layoutManagerSoon
        binding.recyclerFuture.adapter = adapterSoon

        val layoutManagerGenre =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerGenre.layoutManager = layoutManagerGenre
        binding.recyclerGenre.adapter = adapterGenre

        binding.settingsBtn.setOnClickListener {

            controller.navigate(
                R.id.settingsFragment,
            )
        }

        return binding.root
    }


// todo YouTube
//

    ////////////////////////////////


    private fun hasConnection(context: Context): Boolean {
        val cm: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo: NetworkInfo? = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.activeNetworkInfo
        return wifiInfo != null && wifiInfo.isConnected
    }

}