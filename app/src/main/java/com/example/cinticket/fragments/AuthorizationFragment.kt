package com.example.cinticket.fragments

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cinticket.App
import com.example.cinticket.App.Companion.FROM
import com.example.cinticket.R
import com.example.cinticket.Service
import com.example.cinticket.Service.Companion.isValidEmail
import com.example.cinticket.databinding.FragmentAuthorizationBinding
import com.example.cinticket.entities.Account
import com.example.cinticket.sharedpreferences.SharedPrefs
import kotlinx.coroutines.Dispatchers
import com.example.cinticket.post.Appdata.Companion.sendEmailAboutReg
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null

    private val binding: FragmentAuthorizationBinding get() = _binding!!
    private val sharedPreference: SharedPrefs
        get() = (context?.applicationContext as App).sharedPreferences
    private val service: Service
        get() = (context?.applicationContext as App).service

    private val SUCCESS_MESSAGE="Ваш аккаунт успешно создан!."
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        val controller = findNavController()
        controller.popBackStack(R.id.profileFragment, true)
        val editEmail = binding.edtEmail
        val editPassword = binding.edtPassword
        val btnLogin = binding.btnLogin
        val btnSignUp = binding.btnSignUp
        val loginStatus = sharedPreference.getString("login_status")
        if (loginStatus == "1")
            controller.navigate(R.id.profileFragment)




        btnLogin.setOnClickListener {
            val valueEditEmail = editEmail.text.toString()
            val valueEditPassword = editPassword.text.toString()

            if (valueEditEmail == "" || valueEditPassword == "") {
                Toast.makeText(context, "Please Enter Details.", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    val logUser = service.getAccountByEmail(valueEditEmail)
                    val userExist: Boolean =
                        ((logUser != null) and (logUser?.password == valueEditPassword))
                    if (userExist) {
                        withContext(Dispatchers.Main) {
                            sharedPreference.saveString("login_status", "1")
                            if (logUser != null) {
                                sharedPreference.saveString("user_name", logUser.firstName)
                                sharedPreference.saveLong("user_id", logUser.accountId)
                                sharedPreference.saveString("user_email", logUser.email)
                                sharedPreference.saveString("card_number", logUser.cardNumber)
                                sharedPreference.saveString("card_date", logUser.dateOfCard)
                                sharedPreference.saveLong("cvv_code", logUser.CVV_code)
                            }
                            sharedPreference.sharedUser = logUser

                            controller.navigate(R.id.action_authorizationFragment_to_profileFragment)

                        }

                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context, "Enter Valid Email Id & Password.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }

            }
        }


        btnSignUp.setOnClickListener {
            fun_SignUp_PopupWindow()
        }
        return binding.root
    }

    private fun fun_SignUp_PopupWindow() {

        val layoutInflater =
            requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popView: View = layoutInflater.inflate(R.layout.sign_up_window, null)
        val popupWindow = PopupWindow(
            popView,
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT,
            true
        )
        popupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0)

        val btn_dia_submit = popView.findViewById(R.id.btn_dia_submit) as Button
        btn_dia_submit.setOnClickListener {
            val str_dia_email_id =
                popView.findViewById<EditText>(R.id.edt_dia_email_id).text.toString()
            val str_dia_password =
                popView.findViewById<EditText>(R.id.edt_dia_password).text.toString()
            val str_dia_name = popView.findViewById<EditText>(R.id.edt_dia_name).text.toString()

//            if (str_dia_email_id == "" || str_dia_password == "" || !service.isValidEmail(
//                    str_dia_email_id
//                )
//            ) {
//                Toast.makeText(context, "Please Enter Details.", Toast.LENGTH_SHORT).show()
//            } else {
//                popupWindow.dismiss()

            lifecycleScope.launch(Dispatchers.IO) {

                if (str_dia_email_id == "" || str_dia_password == "" || !isValidEmail(str_dia_email_id)) {
                    launch(Dispatchers.Main) {
                        Toast.makeText(context, "Пожалуйста, введите корректные данные.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                else if (service.getAccountByEmail(str_dia_email_id) != null) {
                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Аккаунт с этой почтой уже существует",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    launch(Dispatchers.Main) {
                        popupWindow.dismiss()
                    }

                    service.insertNewAccount(
                        Account(
                            (1000..100000000).random().toLong(),
                            str_dia_name,
                            "Base",
                            str_dia_email_id,
                            str_dia_password,
                            "0",
                            "0",
                            0
                        )
                    )
                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            SUCCESS_MESSAGE,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    sendEmailAboutReg(FROM,"Регистрация",SUCCESS_MESSAGE,str_dia_email_id)
                }
            }
        }
    }

}
