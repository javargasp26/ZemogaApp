package com.example.zemogaapp.data.network.post

import retrofit2.Response
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    suspend fun getPostList(): Response<List<PostResponseModel>>

}