package com.example.zemogaapp.data.network.user

import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("name") val userName: String,
    @SerializedName("id") val userId: Int
)