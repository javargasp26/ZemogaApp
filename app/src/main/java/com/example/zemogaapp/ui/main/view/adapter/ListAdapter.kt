package com.example.zemogaapp.ui.main.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zemogaapp.databinding.RowPostItemBinding
import com.example.zemogaapp.domain.model.Post

class ListAdapter (
    private val viewHolderListener: ListViewHolder.ViewHolderListener
) : RecyclerView.Adapter<ListViewHolder>() {

    private var postList = mutableListOf<Post>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = RowPostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.context = parent.context
        return ListViewHolder(itemBinding, viewHolderListener)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(context, postList[position])
    }

    fun setPostList(postList: List<Post>) {
        this.postList.clear()
        this.postList = postList.toMutableList()
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return postList.isEmpty()
    }

}