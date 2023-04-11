package com.example.zemogaapp.data.network.comment

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentService @Inject constructor(private val commentApi: CommentApi) {
    suspend fun getAllComments(): List<CommentResponseModel> {
        return withContext(Dispatchers.IO) {

            val commentResponse = commentApi.getComments()
            if (commentResponse.isSuccessful){
                commentResponse.body()!!
            }else{
                listOf()
            }
        }
    }
}