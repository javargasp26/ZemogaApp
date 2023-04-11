package com.example.zemogaapp.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zemogaapp.databinding.ActivityMainBinding
import com.example.zemogaapp.domain.model.Post
import com.example.zemogaapp.ui.detail.view.DetailActivity
import com.example.zemogaapp.ui.main.view.adapter.ListAdapter
import com.example.zemogaapp.ui.main.view.adapter.ListViewHolder
import com.example.zemogaapp.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity(), ListViewHolder.ViewHolderListener {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var bindingMainActivity: ActivityMainBinding
    private val postAdapter = ListAdapter(this)
    var showFavorite = false
    var postList = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMainActivity.root)

        mainViewModel.onCreate(false)

        mainViewModel.postListModel.observe(this){
            if (it.isNotEmpty()){
                postList = it.toMutableList()
                setAdapter(it)
                setOnClickListener()
            }
        }

        mainViewModel.isLoading.observe(this, Observer {
            bindingMainActivity.loading.isVisible = it
        })
    }

    private fun setOnClickListener() {
        bindingMainActivity.btnFavorite.setOnClickListener {
            if (showFavorite){
                mainViewModel.onCreate(false)
                bindingMainActivity.btnFavorite.text = "Show favorites"
                showFavorite=false
            }else{
                mainViewModel.onUpdateFavorite()
                bindingMainActivity.btnFavorite.text = "show all"
                showFavorite=true
            }
        }

        bindingMainActivity.btnDeleteAll.setOnClickListener {
            var posts = mutableListOf<Post>()
            postList.forEach {
                if (it.isFavorite){
                    posts.add(it)
                }
            }
            postAdapter.setPostList(posts)
        }

        bindingMainActivity.btnReload.setOnClickListener {
            mainViewModel.onCreate(true)
        }
    }

    private fun setAdapter(postList: List<Post>?) {
        linearLayoutManager = LinearLayoutManager(this)
        bindingMainActivity.rcvPost.layoutManager = linearLayoutManager

        bindingMainActivity.rcvPost.adapter = postAdapter
        postList!!.sortedBy { it.isFavorite }
        postAdapter.setPostList(postList)
        bindingMainActivity.rcvPost.visibility = View.VISIBLE
    }

    override fun onClick(post: Post) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("post", post.postId)
        startActivity(intent)
    }
}