package com.example.cinticket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cinticket.App
import com.example.cinticket.Service
import com.example.cinticket.adapters.SeatsAdapter
import com.example.cinticket.databinding.FragmentHallBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HallFragment : Fragment() {

    private var _binding: FragmentHallBinding? = null
    private val binding get() = _binding!!
    private val service: Service
        get() = (context?.applicationContext as App).service

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHallBinding.inflate(inflater, container, false)
        val movieId = arguments?.getLong("movie_id")
        val sessionDate = arguments?.getString("session_date")
        val time = arguments?.getString("time")
        val controller = findNavController()
        lifecycleScope.launch(Dispatchers.IO) {
            val tmp = service.getNumOfSeats()
            val sessionId=arguments?.getLong("session_id")
            val selledPlaces = service.getSelledPlaces(sessionId!!)
            launch(Dispatchers.Main) {
                binding.gridSeats.adapter = SeatsAdapter(
                    tmp!!,
                    selledPlaces!!,
                    arguments?.getInt("price")!!,
                    binding,
                    controller,
                    sessionId,
                    requireActivity()
                )
            }
        }
        // задаем количество столбцов в Grid
        binding.gridSeats.numColumns = 6

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

