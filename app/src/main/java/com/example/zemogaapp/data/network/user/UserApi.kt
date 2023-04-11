package com.example.zemogaapp.data.network.user

import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponseModel>>

}