package com.example.ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.Comment
import com.example.ecommerce.model.Data
import com.example.ecommerce.model.Keycode
import com.example.ecommerce.model.Rating
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_data_order.view.*
import kotlinx.android.synthetic.main.item_keycode.view.*
import kotlinx.android.synthetic.main.item_keycode.view.code
import kotlinx.android.synthetic.main.item_rating.view.*
import kotlinx.android.synthetic.main.item_rating.view.title

class AdapterDataOrder(val data: List<Data>) :
    RecyclerView.Adapter<AdapterDataOrder.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_data_order, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }
    override fun getItemCount(): Int = data.size

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data) {
            itemView.apply {
                name.text = data.key.software.name
                code.text = data.key.key_code
                price.text = data.key.software.price
            }
        }
    }
}