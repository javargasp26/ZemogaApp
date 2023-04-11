package com.example.zemogaapp.data.network.comment

import com.google.gson.annotations.SerializedName

data class CommentResponseModel(
    @SerializedName("body") val commentDescription: String,
    @SerializedName("id") val commentId: Int,
    @SerializedName("postId") val postId: Int
)