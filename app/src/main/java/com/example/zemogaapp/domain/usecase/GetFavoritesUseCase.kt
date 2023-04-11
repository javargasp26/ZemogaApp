package com.example.zemogaapp.domain.usecase

import com.example.zemogaapp.data.repository.DataRepository
import com.example.zemogaapp.domain.model.Post
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val repository: DataRepository) {
    suspend operator fun invoke():List<Post>{

        var postList = repository.getFavoriteFromDatabase()

        return postList
    }
}