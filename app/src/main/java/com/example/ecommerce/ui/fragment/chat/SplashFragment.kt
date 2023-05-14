package com.example.ecommerce.ui.fragment.chat

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.ecommerce.utils.Constants

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    //Variables
     var splashBinding = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Nothing? {
        // Retrieve user preference for dark mode using SharedPreferences
        val spf: SharedPreferences =
            requireContext().getSharedPreferences(Constants.SPF_NAME, MODE_PRIVATE)
        val darkMode: Boolean = spf.getBoolean(Constants.SPF_DATA, false)

        // Set the appropriate AppCompatDelegate mode based on user preference
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Inflate the layout using View Binding and set it as the content view
        //splashBinding = FragmentSplashBinding.inflate(inflater, container, false)

        // Start the main activity after a 5-second delay
        Handler(Looper.getMainLooper()).postDelayed({
            //startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }, 5000)

        return splashBinding
    }
}
