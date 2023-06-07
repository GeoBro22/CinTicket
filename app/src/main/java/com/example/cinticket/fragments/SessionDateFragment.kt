package com.example.cinticket.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinticket.App
import com.example.cinticket.Service
import com.example.cinticket.adapters.SessionAdapter
import com.example.cinticket.databinding.FragmentSessionDateBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SessionDateFragment : Fragment() {
    private lateinit var binding: FragmentSessionDateBinding
    private val service: Service
        get() = (context?.applicationContext as App).service
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSessionDateBinding.inflate(inflater, container, false)
        val controller = findNavController()
        val sessionAdapter = SessionAdapter(controller,arguments?.getLong("movie_id"),service,requireActivity())

        lifecycleScope.launch(Dispatchers.IO) {
            val sessions = arguments?.getLong("movie_id")?.let { service.getDatesForMovie(it) }
            launch(Dispatchers.Main) {
                if (sessions != null) {
                    sessionAdapter.sessionsDates = sessions.toList()
                    Log.d("ttt", sessions.toString())
                }
            }
        }

        val layoutManagerDates =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerDates.layoutManager = layoutManagerDates
        binding.recyclerDates.adapter = sessionAdapter
        return binding.root
    }
}