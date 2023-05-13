package com.example.ecommerce.ui.fragment.order


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
import com.example.ecommerce.viewmodel.AddFavoriteViewModel
import com.example.ecommerce.viewmodel.DetailSoftwareViewModel
import com.example.ecommerce.viewmodel.LoginViewModel
import com.example.ecommerce.viewmodel.OrderDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_order.*

import kotlinx.android.synthetic.main.fragment_detail_product.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import www.sanju.motiontoast.MotionToast


class OrderDetailFragment : Fragment() {
    private val orderDetailViewModel: OrderDetailViewModel by viewModel { parametersOf(id) }
    private var args: ListOrderFragmentArgs? = null
    var id: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_order, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        


        args = arguments?.let { ListOrderFragmentArgs.fromBundle(it) }

        id = args?.order?.id

        println("$id")

        orderDetailViewModel.orderDetailLiveData.observe(viewLifecycleOwner) {
            /*(it.status).also { name -> tv_status.text = name }
            (it.message).also { p -> tv_message.text = p }
            (it.id.toString()).also { int -> tv_order_id.text = int }*/

            val adapterKeyCode: AdapterDataOrder by inject { parametersOf(it.data) }
            rv_order_details.adapter = adapterKeyCode

        }
        rv_order_details.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }



}