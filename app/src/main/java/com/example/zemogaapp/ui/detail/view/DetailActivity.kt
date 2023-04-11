package com.example.zemogaapp.ui.detail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.zemogaapp.R
import com.example.zemogaapp.databinding.ActivityDetailBinding
import com.example.zemogaapp.domain.model.Post
import com.example.zemogaapp.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var bindingDetailActivity: ActivityDetailBinding
    private lateinit var post: Post
    private val detailViewModel: MainViewModel by viewModels()
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetailActivity = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetailActivity.root)

        val postId = intent.getIntExtra("post",0)

        detailViewModel.onGetPost(postId)

        detailViewModel.postModel.observe(this){
            post = it
            isFavorite = post.isFavorite
            setView()
            setComments()
            setFavorite()
            setOnClickListener()
        }

        detailViewModel.isLoading.observe(this, Observer {
            bindingDetailActivity.loading.isVisible = it
        })

    }

    private fun setOnClickListener() {
        bindingDetailActivity.imgFavorite.setOnClickListener {
            if (isFavorite){
                bindingDetailActivity.imgFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_star_border_24
                    )
                )
                isFavorite=false
            }else{
                bindingDetailActivity.imgFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_star_24
                    )
                )
                isFavorite=true
            }
            detailViewModel.UpdateFavoritePostById(post.postId, isFavorite)
        }
    }

    private fun setFavorite() {
        if (isFavorite){
            bindingDetailActivity.imgFavorite.setImageResource(R.drawable.ic_baseline_star_24)
        }else{
            bindingDetailActivity.imgFavorite.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
    }

    private fun setComments() {
        var comments = ""
        post.commentList.forEach {
            comments = comments+ "\n" +it.comment
        }
        bindingDetailActivity.tvComments.text = comments
    }

    private fun setView() {
        bindingDetailActivity.tvAuthorName.text = post.userName
        bindingDetailActivity.tvPostTitle.text = post.postTitle
        bindingDetailActivity.tvPostDescription.text = post.postDescription
    }
}