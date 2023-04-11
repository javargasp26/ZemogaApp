package com.example.zemogaapp.data.network.post

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PostService @Inject constructor(private val postApi: PostApi) {
    suspend fun getAllPost(): List<PostResponseModel> {
        return withContext(Dispatchers.IO) {

            val postResponse = postApi.getPostList()
            if (postResponse.isSuccessful){
                postResponse.body()!!
            }else{
                listOf()
            }
        }
    }
}