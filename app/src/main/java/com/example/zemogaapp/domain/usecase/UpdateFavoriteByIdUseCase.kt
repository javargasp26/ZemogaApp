package com.example.zemogaapp.domain.usecase

import com.example.zemogaapp.data.repository.DataRepository
import com.example.zemogaapp.domain.model.Post
import javax.inject.Inject

class UpdateFavoriteByIdUseCase  @Inject constructor(private val repository: DataRepository) {
    suspend operator fun invoke(postId: Int, favorite:Boolean): Post {

        return repository.updateFavoriteByIdFromDatabase(postId, favorite)

    }
}