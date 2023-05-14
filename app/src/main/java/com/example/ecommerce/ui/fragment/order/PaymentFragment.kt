package com.example.ecommerce.ui.fragment.order


import android.app.Activity
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_order.*

import kotlinx.android.synthetic.main.fragment_detail_product.*
import kotlinx.android.synthetic.main.fragment_detail_user.*
import kotlinx.android.synthetic.main.fragment_payment.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import www.sanju.motiontoast.MotionToast


class PaymentFragment : Fragment() {

    private var args: PaymentFragmentArgs? = null
    private val cartViewModel: CartViewModel by viewModel()
    var id: Int? = null
    var total: Float? = null
    var totalItem: Float? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel.cart()
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/shopping-android-app-25e4a.appspot.com/o/z4343693855335_4304a4ce0d1bc5dbab2a5b59047a5a9a.jpg?alt=media&token=84d06444-6257-4f54-863b-77c69c2cce90").into(qr_code);
        cartViewModel.cartLiveData.observe(viewLifecycleOwner) {

            total = 0f
            totalItem = 0f
            for (cartItem in it) {
                total = total?.plus(cartItem.software.price.toFloat())?.times(cartItem.quantity)
            }
            (total.toString()).also { name -> amount.text = name }
        }
        pay_button.setOnClickListener{
            cartViewModel.orderCart()
            Toast.makeText(context,"Order successfully, plese wait 5min!!", Toast.LENGTH_SHORT).show()
            it.findNavController().popBackStack()
        }
    }
}