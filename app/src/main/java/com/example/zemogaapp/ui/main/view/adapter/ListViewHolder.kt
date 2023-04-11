package com.example.zemogaapp.ui.main.view.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.zemogaapp.databinding.RowPostItemBinding
import com.example.zemogaapp.domain.model.Post

class ListViewHolder(private val itemBinding: RowPostItemBinding, viewHolderListener: ViewHolderListener) :
    RecyclerView.ViewHolder(itemBinding.root) {

    val holder: ViewHolderListener = viewHolderListener

    fun bind(context: Context, post: Post) {
        itemBinding.tvTitle.text = post.postTitle

        itemBinding.cntInfo.setOnClickListener {
            holder.onClick(post)
        }
    }

    interface ViewHolderListener {
        fun onClick(
            post: Post
        )
    }
}