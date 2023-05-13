package com.example.ecommerce.ui.fragment.detail


import android.app.Activity
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.model.OrderDetail
import com.example.ecommerce.ui.adapter.*
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.viewmodel.*
import kotlinx.android.synthetic.main.fragment_detail_order.*

import kotlinx.android.synthetic.main.fragment_detail_product.*
import kotlinx.android.synthetic.main.fragment_detail_user.*
import kotlinx.android.synthetic.main.item_comment.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import www.sanju.motiontoast.MotionToast


class DetailUserFragment : Fragment() {
    private val detailUserViewModel: DetailUserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_user, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailUserViewModel.detailUserLiveData.observe(viewLifecycleOwner) {
            (it.first_name).also { name -> edit_first_name.text = name }
            (it.last_name).also { p -> edit_last_name.text = p }
            (it.email).also { email -> edit_email.text = email }
            (it.username).also { int -> edit_username.text = int }
        }
    }



}