package com.example.zemogaapp.data.repository

import com.example.zemogaapp.data.local.dao.PostDao
import com.example.zemogaapp.data.local.entity.PostEntity
import com.example.zemogaapp.data.network.comment.CommentApi
import com.example.zemogaapp.data.network.post.PostApi
import com.example.zemogaapp.data.network.user.UserApi
import com.example.zemogaapp.domain.model.Comment
import com.example.zemogaapp.domain.model.Post
import com.example.zemogaapp.domain.model.User
import com.example.zemogaapp.domain.model.toDomain
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val postApi: PostApi,
    private val userApi: UserApi,
    private val commentApi: CommentApi,
    private val postDataBase: PostDao
){
    suspend fun getPost(): List<Post> {
        val response = postApi.getPostList()
        return response.body()!!.map { it.toDomain() }
    }

    suspend fun getUsers(): List<User> {
        val response = userApi.getUsers()
        return response.body()!!.map { it.toDomain() }
    }

    suspend fun getComments(): List<Comment> {
        val response = commentApi.getComments()
        return response.body()!!.map { it.toDomain() }
    }

    suspend fun getPostFromDataBase():List<Post>{
        val response = postDataBase.getAllPost()
        return response.map { it.toDomain() }
    }

    suspend fun insertPost(posts: List<PostEntity>) {
        postDataBase.insertAllPost(posts)
    }

    suspend fun getPostById(postId: Int): Post {
        return postDataBase.getPost(postId)
    }

    suspend fun updateFavoriteByIdFromDatabase(id:Int, favorite:Boolean):Post{
        postDataBase.updateFavoriteById(id, favorite)
        val response = postDataBase.getPost(id)
        return response
    }

    suspend fun getFavoriteFromDatabase():List<Post>{
        val response = postDataBase.getFavorites()
        return response.map { it.toDomain() }
    }

    suspend fun clearPost() {
        postDataBase.clearPost()
    }
}