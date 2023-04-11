package com.example.zemogaapp.data.network.comment

import retrofit2.Response
import retrofit2.http.GET

interface CommentApi {

    @GET("comments")
    suspend fun getComments(): Response<List<CommentResponseModel>>

}