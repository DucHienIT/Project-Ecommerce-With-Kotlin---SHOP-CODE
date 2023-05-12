package com.example.ecommerce.ui.fragment.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerce.R
import com.example.ecommerce.model.User
import com.example.ecommerce.viewmodel.LoadUserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var loadUserViewModel: LoadUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserViewModel = ViewModelProvider(this).get(LoadUserViewModel::class.java)
        //observeUserInformation()
        setupButtonListeners()
    }

    private fun observeUserInformation() {
        loadUserViewModel.userInformationData.observe(viewLifecycleOwner, { user ->
            user?.let {
                setUserInformation(user)
            }
        })
    }

    private fun setUserInformation(user: User) {
        edit_username.setText(user.username)
        edit_email.setText(user.email)
        edit_first_name.setText(user.first_name)
        edit_last_name.setText(user.last_name)
    }

    private fun setupButtonListeners() {
        btn_update.setOnClickListener {
            // Perform the update operation here
        }

        btn_cancel.setOnClickListener {
            // Handle cancel button click here
        }
    }
}
