package com.example.cinticket.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cinticket.R
import com.example.cinticket.Service
import com.example.cinticket.databinding.TimeModelBinding
import com.example.cinticket.entities.Ticket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TimeAdapter(
    private val navController: NavController,
    private val movieId: Long,
    private val sessionDate: String,
    private val service: Service,
) :
    RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {
    var times: List<String> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = times.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TimeModelBinding.inflate(inflater, parent, false)
        return TimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        val timeOfSession = times[position]
        val bundle = Bundle()

        with(holder.binding) {
            GlobalScope.launch(Dispatchers.IO) {
                val sessionId = service.getSessionId(movieId!!, sessionDate!!, timeOfSession!!)
                val ticketPrice = sessionId?.price
                if (ticketPrice != null) {
                    bundle.putLong("session_id", sessionId.sessionId)
                    bundle.putInt("price", ticketPrice.toInt())
                }
                launch(Dispatchers.Main) {
                    price.text = ticketPrice.toString() + "Р"
                }
            }
            time.text = timeOfSession
            //bundle.putInt("price", this@TimeAdapter.price.toInt())
            bundle.putString("session_date", sessionDate)
            bundle.putLong("movie_id", movieId)
            bundle.putString("time", timeOfSession)
            holder.itemView.setOnClickListener {
                navController.navigate(
                    R.id.hallFragment, bundle
                )
            }
        }


    }


    class TimeViewHolder(
        val binding: TimeModelBinding
    ) : RecyclerView.ViewHolder(binding.root)
}