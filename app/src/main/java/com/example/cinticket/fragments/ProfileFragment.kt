package com.example.cinticket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cinticket.R
import com.example.cinticket.databinding.FragmentProfileBinding
import com.example.cinticket.sharedpreferences.SharedPrefs

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding: FragmentProfileBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val controller = findNavController()
        controller.popBackStack(R.id.authorizationFragment, true)
        val sharedPreference = SharedPrefs(requireActivity())
        binding.name.text = "Имя: " + sharedPreference.getString("user_name")
        binding.email.text = "Почта: " + sharedPreference.getString("user_email")
        val btnLogOut = binding.btnLogOut

        btnLogOut.setOnClickListener {
            sharedPreference.clearSharedPreference()
            sharedPreference.saveString("login_status", "0")
            Toast.makeText(context, "User LogOut Successfully.", Toast.LENGTH_SHORT).show()
            controller.navigate(R.id.authorizationFragment)
        }
        return binding.root
    }
}