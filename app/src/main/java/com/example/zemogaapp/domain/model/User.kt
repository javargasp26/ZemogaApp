package com.example.zemogaapp.domain.model

import com.example.zemogaapp.data.network.user.UserResponseModel

data class User (
    val userId:Int,
    val userName: String
)

fun UserResponseModel.toDomain() = User(
    userId,
    userName
)