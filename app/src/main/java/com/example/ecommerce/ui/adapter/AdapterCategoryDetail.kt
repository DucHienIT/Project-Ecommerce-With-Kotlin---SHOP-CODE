package com.example.ecommerce.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.Software
import com.example.ecommerce.ui.fragment.category.CategoryListFragmentDirections
import com.example.ecommerce.ui.fragment.home.ImageLoading
import kotlinx.android.synthetic.main.category_detail_item.view.*
import kotlinx.android.synthetic.main.category_detail_item.view.price

class AdapterCategoryDetail(
    private val Software: List<Software>,
    private val imageLoading: ImageLoading
) :
    RecyclerView.Adapter<AdapterCategoryDetail.ListFavoriteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListFavoriteViewHolder {
        return ListFavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_detail_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ListFavoriteViewHolder,
        position: Int
    ) {
        val Software = Software[position]
        holder.bind(Software, imageLoading)
    }

    override fun getItemCount(): Int {
        return Software.size
    }

    class ListFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(Software: Software, imageLoading: ImageLoading) {
            itemView.apply {
                //imageLoading.load(image, Software.image_url)
                text.text = Software.name
                price.text = Software.price
                setOnClickListener {
                    findNavController().navigate(
                    CategoryListFragmentDirections.actionCategoryListFragmentToDetailSoftwareFragment3(Software)
                    )
                }


            }
        }
    }


}