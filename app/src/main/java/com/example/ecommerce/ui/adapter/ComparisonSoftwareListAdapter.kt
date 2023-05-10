package com.example.ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.Software
import com.example.ecommerce.ui.fragment.comparison.ComparisonListFragmentDirections
import com.example.ecommerce.ui.fragment.home.ImageLoading
import kotlinx.android.synthetic.main.item_comparison_product_list.view.*
import kotlinx.android.synthetic.main.item_property_product.view.text_title
import java.util.ArrayList

class ComparisonSoftwareListAdapter(private val imageLoading: ImageLoading) :
    RecyclerView.Adapter<ComparisonSoftwareListAdapter.ComparisonSoftwareListViewHolder>() {
    var software = ArrayList<Software>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComparisonSoftwareListViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_property_product,
                    parent,
                    false
                )
        return ComparisonSoftwareListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComparisonSoftwareListViewHolder, position: Int) {
        val Software = software[position]
        holder.bind(Software, imageLoading)
    }

    override fun getItemCount(): Int = software.size


    class ComparisonSoftwareListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(software: Software, imageLoading: ImageLoading) {
            itemView.apply {
                setOnClickListener {
                    findNavController().navigate(
                        ComparisonListFragmentDirections.actionComparisonListFragmentToComparisonFragment(
                            software
                        )
                    )
                }
                //imageLoading.load(imageView, Software.image_url)
                text_title.text = software.name
                text_price.text = software.price
            }
        }
    }
}