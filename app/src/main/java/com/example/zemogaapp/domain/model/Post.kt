package com.example.zemogaapp.domain.model

import android.os.Parcelable
import com.example.zemogaapp.data.local.entity.PostEntity
import com.example.zemogaapp.data.network.post.PostResponseModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Post (
    val postId:Int,
    val postTitle:String,
    val postDescription: String,
    var isFavorite:Boolean = false,
    var commentList: @RawValue MutableList<Comment>,
    var userId: Int,
    var userName:String = ""
):Parcelable

fun PostResponseModel.toDomain() = Post(
    postId,
    postTitle,
    postDescription,
    false,
    mutableListOf(),
    userId,
    ""
)

fun PostEntity.toDomain() = Post(
    postId,
    postTitle,
    postDescription,
    isFavorite,
    commentList as MutableList<Comment>,
    userId,
    userName
)