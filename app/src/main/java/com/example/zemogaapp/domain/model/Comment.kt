package com.example.zemogaapp.domain.model

import android.os.Parcelable
import com.example.zemogaapp.data.network.comment.CommentResponseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment (
    val commentId: Int,
    val comment: String,
    val postId: Int
):Parcelable

fun CommentResponseModel.toDomain() = Comment(
    commentId,
    commentDescription,
    postId
)