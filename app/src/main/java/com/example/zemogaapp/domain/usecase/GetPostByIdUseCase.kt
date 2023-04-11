package com.example.zemogaapp.domain.usecase

import com.example.zemogaapp.data.repository.DataRepository
import com.example.zemogaapp.domain.model.Post
import javax.inject.Inject

class GetPostByIdUseCase  @Inject constructor(private val repository: DataRepository){
    suspend operator fun invoke(postId:Int): Post {

        return repository.getPostById(postId)
    }

}
