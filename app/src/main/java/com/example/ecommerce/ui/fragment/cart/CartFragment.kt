package com.example.ecommerce.ui.fragment.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.ui.adapter.AdapterListFavorite
import com.example.ecommerce.ui.adapter.CartListAdapter
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.viewmodel.CartViewModel
import com.example.ecommerce.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CartFragment : Fragment() {
    private val cartViewModel: CartViewModel by viewModel()

    var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel.cart()
        if (TokenHolder.access_token!= null) {
            cartViewModel.cartLiveData.observe(viewLifecycleOwner) {
                val cartAdapter: CartListAdapter by inject { parametersOf(it) }
                cart.adapter = cartAdapter
                cart.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            button_order.setOnClickListener{

                //cartViewModel.cart()
                try {
                    //val directions = CartFragmentDirections.actionCartFragmentToPaymentFragment()
                    findNavController().navigate(R.id.chartFragment)
                    //cartViewModel.orderCart()
                } catch (exception: Exception) {
                    println(exception.toString())
                } finally {
                    // mã được thực thi sau khi mã trong khối try hoặc catch thực thi xong
                }

            }
        }
    }


}
