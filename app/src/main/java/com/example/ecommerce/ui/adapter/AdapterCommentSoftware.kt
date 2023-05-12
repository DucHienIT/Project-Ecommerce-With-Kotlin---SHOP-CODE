package com.example.ecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.Comment
import com.example.ecommerce.model.Rating
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_rating.view.*
import kotlinx.android.synthetic.main.item_rating.view.title

class AdapterCommentSoftware(val comment: List<Comment>) :
    RecyclerView.Adapter<AdapterCommentSoftware.CommentSoftwareViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentSoftwareViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentSoftwareViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentSoftwareViewHolder, position: Int) {
        val comment = comment[position]
        holder.bind(comment)
    }
    override fun getItemCount(): Int = comment.size

    class CommentSoftwareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: Comment) {
            itemView.apply {
                username.text=comment.user
                title.text = comment.text
                creat_at.text = comment.created_at.take(10)
            }
        }
    }
}