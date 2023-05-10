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
    private val imageLoading: ImageLoading
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
        holder.bind(list, imageLoading)
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
                //imageLoading.load(imageView, "http://192.168.8.157:8000${favoriteList.Software.image_url}")
                //Log.i("TAG", "http://192.168.8.157:8000${favoriteList.Software.image_url}")
                text_title.text = favoriteList.software.name
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