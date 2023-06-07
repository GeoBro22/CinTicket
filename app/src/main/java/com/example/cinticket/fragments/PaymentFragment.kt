package com.example.cinticket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cinticket.App
import com.example.cinticket.R
import com.example.cinticket.Service
import com.example.cinticket.accounts.entities.AccountUpdateCardInfoTuple
import com.example.cinticket.boughttickets.entities.TicketInsertTuple
import com.example.cinticket.databinding.FragmentPaymentBinding
import com.example.cinticket.post.Appdata.Companion.sendEmailAboutReg
import com.example.cinticket.post.Appdata.Companion.sendEmailTickets
import com.example.cinticket.sharedpreferences.SharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep


class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private val service: Service
        get() = (context?.applicationContext as App).service
    private val sharedPreference: SharedPrefs
        get() = (context?.applicationContext as App).sharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        val controller = findNavController()
        binding.finalPrice.text=arguments?.getString("price")
        val authStatus = sharedPreference.getString("login_status")
        if (authStatus == "1") {
            binding.email.setText(sharedPreference.getString("user_email"))
            if (sharedPreference.getString("card_number") != "0") {
                binding.cardNumber.setText(sharedPreference.getString("card_number"))
                binding.cardDate.setText(sharedPreference.getString("card_date"))
                binding.CVVCode.setText(sharedPreference.getLong("cvv_code").toString())
            }
        }
        val chosenTickets = arguments?.getIntegerArrayList("chosen_tickets")
        val sessionId = arguments?.getLong("session_id")
        binding.payBtn.setOnClickListener {
            if (binding.cardNumber.text.toString().length == 16 && binding.cardDate.text.toString().length == 5 && service.isValidDate(binding.cardDate.text.toString()) && binding.CVVCode.text.toString().length == 3 && service.isValidEmail(binding.email.text.toString())) {
                var updatedAccountCardInfo = AccountUpdateCardInfoTuple(
                    0L, "0", "1", 0L
                )

                if (authStatus == "1") {
                    if (sharedPreference.getString("card_number") == "0") {
                        sharedPreference.saveString(
                            "card_number",
                            binding.cardNumber.text.toString()
                        )
                        sharedPreference.saveString("card_date", binding.cardDate.text.toString())
                        sharedPreference.saveLong(
                            "cvv_code",
                            binding.CVVCode.text.toString().toLong()
                        )
                        updatedAccountCardInfo = AccountUpdateCardInfoTuple(
                            sharedPreference.getLong("user_id")!!,
                            binding.cardNumber.text.toString(),
                            binding.cardDate.text.toString(),
                            binding.CVVCode.text.toString().toLong()
                        )
                    }
                }
                sendEmailTickets("CinTicket", "Tickets", chosenTickets!!, binding.email.text.toString())
                lifecycleScope.launch(Dispatchers.IO) {
                    service.updateAccountCardInfo(updatedAccountCardInfo)
                    for (i in 0 until (chosenTickets!!.size))
                        service.insertNewTicket(
                            TicketInsertTuple(
                                sessionId!!,
                                chosenTickets[i].toLong()
                            )
                        )

                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Билеты отправлены на вашу почту.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                sleep(500L)
                controller.navigate(R.id.action_paymentFragment_to_mainFragment)

            } else
                Toast.makeText(activity, "Введены некорректные данные.", Toast.LENGTH_SHORT).show()

        }

        return binding.root
    }


}


