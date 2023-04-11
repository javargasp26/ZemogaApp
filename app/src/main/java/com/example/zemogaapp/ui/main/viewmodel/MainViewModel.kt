package com.example.zemogaapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zemogaapp.domain.model.Post
import com.example.zemogaapp.domain.usecase.GetFavoritesUseCase
import com.example.zemogaapp.domain.usecase.GetPostByIdUseCase
import com.example.zemogaapp.domain.usecase.GetPostUseCase
import com.example.zemogaapp.domain.usecase.UpdateFavoriteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var getPostUseCase: GetPostUseCase,
    private var getPostByIdUseCase: GetPostByIdUseCase,
    private var updateFavoriteByIdUseCase: UpdateFavoriteByIdUseCase,
    private var getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    val postListModel = MutableLiveData<List<Post>>()
    val isLoading = MutableLiveData<Boolean>()
    val postModel = MutableLiveData<Post>()

    fun onCreate(reload: Boolean) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getPostUseCase.invoke(reload)

            if(result.isNotEmpty()){
                postListModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun onGetPost(postId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getPostByIdUseCase.invoke(postId)

            postModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun UpdateFavoritePostById(id:Int, favorite:Boolean){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = updateFavoriteByIdUseCase(id, favorite)

            postModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun onUpdateFavorite() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getFavoritesUseCase()

            if (!result.isNullOrEmpty()) {
                postListModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}