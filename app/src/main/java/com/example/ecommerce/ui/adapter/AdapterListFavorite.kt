package com.example.ecommerce.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.FavoriteList
import com.example.ecommerce.ui.fragment.account.FavoriteFragmentDirections
import com.example.ecommerce.ui.fragment.home.ImageLoading
import kotlinx.android.synthetic.main.item_favorite.view.*

class AdapterListFavorite(
    private val favoriteList: List<FavoriteList>,
) :
    RecyclerView.Adapter<AdapterListFavorite.ListFavoriteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListFavoriteViewHolder {
        return ListFavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_favorite,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ListFavoriteViewHolder,
        position: Int
    ) {
        val list = favoriteList[position]
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    class ListFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favoriteList: FavoriteList, imageLoading: ImageLoading) {
            itemView.apply {
                /*
                you must replace your ip address
                 */
                //imageLoading.load(imageView, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.simplilearn.com%2Fimage-processing-article&psig=AOvVaw2nosyppoqoQxAXfAmxduEI&ust=1683992833232000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCID0rKGQ8P4CFQAAAAAdAAAAABAE")
                //Log.i("TAG", "http://192.168.8.157:8000${favoriteList.Software.image_url}")
                text_title.text = "favoriteList.software.name"
                setOnClickListener {
                    findNavController().navigate(
                        FavoriteFragmentDirections.actionFavoriteFragmentToDetailSoftwareFragment2(
                            favoriteList.software
                        )
                    )
                }
            }
        }
    }
}