package com.example.ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.Comment
import com.example.ecommerce.model.Keycode
import com.example.ecommerce.model.Rating
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_keycode.view.*
import kotlinx.android.synthetic.main.item_rating.view.*
import kotlinx.android.synthetic.main.item_rating.view.title

class AdapterKeyCode(val keycode: List<Keycode>) :
    RecyclerView.Adapter<AdapterKeyCode.KeyCodeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyCodeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return KeyCodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: KeyCodeViewHolder, position: Int) {
        val keycode = keycode[position]
        holder.bind(keycode)
    }
    override fun getItemCount(): Int = keycode.size

    class KeyCodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(keycode: Keycode) {
            itemView.apply {
                software_name.text = keycode.software.name
                code.text = keycode.code
            }
        }
    }
}