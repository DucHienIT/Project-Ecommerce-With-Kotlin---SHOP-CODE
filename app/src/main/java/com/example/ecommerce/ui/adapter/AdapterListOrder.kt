package com.example.ecommerce.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.Category
import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.model.Order
import com.example.ecommerce.ui.fragment.account.FavoriteFragmentDirections
import com.example.ecommerce.ui.fragment.home.ImageLoading
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_favorite.view.*
import kotlinx.android.synthetic.main.item_order.view.*

class AdapterListOrder(private val listOrder: List<Order>) :
    RecyclerView.Adapter<AdapterListOrder.ListOrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOrderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ListOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListOrderViewHolder, position: Int) {
        val order = listOrder[position]
        println(order.order_date)
        holder.bind(order)

    }

    override fun getItemCount(): Int = listOrder.size


    class ListOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(order: Order) {
            itemView.apply {
                creat_at.text = order.order_date
                status.text = order.status
            }
        }
    }
}