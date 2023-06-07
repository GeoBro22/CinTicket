package com.example.cinticket.adapters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinticket.Service
import com.example.cinticket.databinding.SessionModelBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SessionAdapter(
    private val navController: NavController,
    private val movieId: Long?,
    private val service: Service,
    val context: Context
) :
    RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {
    var sessionsDates: List<String> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = sessionsDates.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SessionModelBinding.inflate(inflater, parent, false)
        return SessionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {

        val dateForAdapter = sessionsDates[position]
        val dateOfSession = sessionsDates[position].split("/").toTypedArray()
        with(holder.binding) {
            date.text = dateOfSession[0]

            var tmp= when (dateOfSession[1].toInt()) {
                1 -> "январь"
                2 -> "февраль"
                3 -> "март"
                4 -> "апрель"
                5 -> "май"
                6 -> "июнь"
                7 -> "июль"
                8 -> "август"
                9 -> "сентябрь"
                10 -> "октябрь"
                11 -> "ноябрь"
                12 -> "декабрь"
                else -> ""
            }
            month.text = tmp
            val timeAdapter = TimeAdapter(navController,movieId!!,sessionsDates[position],service)

            GlobalScope.launch(Dispatchers.IO) {
                val times = movieId?.let { service.getTimesForMovie(it, dateForAdapter) }
                launch(Dispatchers.Main) {
                    if (times != null) {
                        timeAdapter.times = times
                    }
                }
            }
            val layoutManagerDates =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerTimes.layoutManager = layoutManagerDates
            recyclerTimes.adapter = timeAdapter
        }

    }


    class SessionViewHolder(
        val binding: SessionModelBinding
    ) : RecyclerView.ViewHolder(binding.root)
}