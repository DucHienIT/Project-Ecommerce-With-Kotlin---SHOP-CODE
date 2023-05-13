package com.example.ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.AddCartMessage
import com.example.ecommerce.model.CartItem
import com.example.ecommerce.model.Login
import com.example.ecommerce.repository.AddCartRepository
import com.example.ecommerce.ui.fragment.cart.CartFragment
import com.example.ecommerce.ui.fragment.cart.CartFragmentDirections
import com.example.ecommerce.ui.fragment.home.HomeFragmentDirections
import com.example.ecommerce.utils.Observer
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.utils.singleHelper
import com.example.ecommerce.viewmodel.AddCartViewModel
import com.example.ecommerce.viewmodel.LoginViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.fragment_detail_product.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartListAdapter(val cart: List<CartItem>) : RecyclerView.Adapter<CartListAdapter.CartViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cart[position]
        holder.bind(cartItem)

    }

    override fun getItemCount(): Int = cart.size



    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cart: CartItem) {
            itemView.apply {
                setOnClickListener {
                    val directions =
                        CartFragmentDirections.actionCartFragmentToDetailCartFragment(cart)
                    it.findNavController().navigate(directions)}

                //imageLoading.load(image_category, category.image)
                title.text = cart.software.name
                Picasso.get().load(cart.software.image_url).into(image);
                count.text = cart.quantity.toString()
                total2.text =
                    (cart.software.price.toFloat() * cart.software.quantity.toInt()).toString()


            }
        }
    }
}