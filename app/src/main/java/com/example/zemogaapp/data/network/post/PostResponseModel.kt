package com.example.zemogaapp.data.network.post

import com.google.gson.annotations.SerializedName

data class PostResponseModel(
    @SerializedName("title") val postTitle: String,
    @SerializedName("id") val postId: Int,
    @SerializedName("body") val postDescription: String,
    @SerializedName("userId") val userId:Int
)