package com.example.cinticket.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.navigation.NavController
import com.example.cinticket.R
import com.example.cinticket.databinding.FragmentHallBinding
import com.example.cinticket.databinding.ItemSeatBinding

class SeatsAdapter(
    private val seats: List<Long>,
    private val selledPlaces: List<Long>,
    private val price: Int,
    private val binder: FragmentHallBinding,
    private val controller: NavController,
    private val sessionId: Long,
    private val context: Context
) : BaseAdapter() {

    private val chosenTickets = ArrayList<Int>()
    override fun getCount(): Int = seats.size

    override fun getItem(position: Int): Any = seats[position]

    override fun getItemId(position: Int): Long = seats[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val seat = seats[position]
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.item_seat, parent, false)
        val bundle = Bundle()
        val bindingElement = ItemSeatBinding.bind(view)
        val bindingFragment = binder
        if (seat in selledPlaces)
            bindingElement.movieImage.imageTintList = ColorStateList.valueOf(Color.GRAY)
        else
            bindingElement.movieImage.imageTintList = ColorStateList.valueOf(Color.GREEN)


        view.setOnClickListener {
            val prvPrice = bindingFragment.price.text.toString()
                .substring(0, bindingFragment.price.text.toString().length - 1)
//            if (prvPrice=="")
//                prvPrice="0"
            when (bindingElement.movieImage.imageTintList) {
                ColorStateList.valueOf(Color.GREEN) -> {
                    bindingElement.movieImage.imageTintList = ColorStateList.valueOf(Color.RED)
                    bindingFragment.price.text = (prvPrice.toInt() + price).toString() + "₽"
                    chosenTickets.add(position + 1)
                }

                ColorStateList.valueOf(Color.RED) -> {
                    bindingElement.movieImage.imageTintList = ColorStateList.valueOf(Color.GREEN)
                    bindingFragment.price.text = (prvPrice.toInt() - price).toString() + "₽"
                    chosenTickets.remove(position + 1)
                }

            }
            //val tmp =bindingFragment.price.text.toString().substring(0,bindingFragment.price.text.toString().length-1)

        }
        bindingFragment.buyBtn.setOnClickListener {
            if (chosenTickets.size == 0)
                Toast.makeText(context, "Choose ticket.", Toast.LENGTH_SHORT).show()
            else {
                bundle.putIntegerArrayList("chosen_tickets", chosenTickets)
                bundle.putLong("session_id", sessionId)
                bundle.putString("price", bindingFragment.price.text.toString())

                controller.navigate(R.id.paymentFragment, bundle)

            }
        }
        return view
    }
}