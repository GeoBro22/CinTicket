package com.example.cinticket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cinticket.R
import com.example.cinticket.databinding.FragmentSettingsBinding
import com.example.cinticket.sharedpreferences.SharedPrefs


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null

    private val binding: FragmentSettingsBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val controller = findNavController()
        binding.supportBtn.visibility=GONE
        val sharedPreference = SharedPrefs(requireActivity())
        binding.button.setOnClickListener {
            val str_login_status = sharedPreference.getString("login_status")
            if (str_login_status == "1") {
                controller.navigate(R.id.profileFragment)
            } else
                controller.navigate(R.id.authorizationFragment)
        }
        return binding.root
    }
}