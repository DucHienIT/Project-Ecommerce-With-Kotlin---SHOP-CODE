package com.example.ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.Software
import com.example.ecommerce.ui.fragment.home.HomeFragmentDirections
import com.example.ecommerce.ui.fragment.home.ImageLoading
import kotlinx.android.synthetic.main.amazing_item.view.*

class AmazingAdapter(private val Software: List<Software>, private val imageLoading: ImageLoading) :
    RecyclerView.Adapter<AmazingAdapter.AmazingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmazingViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.amazing_item,
                    parent,
                    false
                )
        return AmazingViewHolder(view)
    }

    override fun onBindViewHolder(holder: AmazingViewHolder, position: Int) {
        val amazing = Software[position]
        holder.bind(amazing, imageLoading)
    }

    override fun getItemCount(): Int = Software.size


    class AmazingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(Software: Software, imageLoading: ImageLoading) {
            itemView.apply {
                setOnClickListener {
                    val directions =
                        HomeFragmentDirections.actionHomeFragmentToDetailSoftwareFragment(Software)
                    it.findNavController().navigate(directions)
                }
                imageLoading.load(amazing_image, Software.image_url)
                amazing_text.text = Software.name
                price.text = Software.price
                amount.text = Software.quantity.toString()
            }
        }
    }
}