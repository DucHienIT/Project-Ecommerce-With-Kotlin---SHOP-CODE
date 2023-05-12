package com.example.ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.Rating
import kotlinx.android.synthetic.main.item_rating.view.*

class AdapterRatingSoftware(val rating: List<Rating>) :
    RecyclerView.Adapter<AdapterRatingSoftware.RatingSoftwareViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingSoftwareViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rating, parent, false)
        return RatingSoftwareViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatingSoftwareViewHolder, position: Int) {
        val rating = rating[position]
        holder.bind(rating)
    }

    override fun getItemCount(): Int = rating.size

    class RatingSoftwareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rating: Rating) {
            itemView.apply {
                title.text = rating.title
                value.progress = rating.value
            }

        }

    }
}