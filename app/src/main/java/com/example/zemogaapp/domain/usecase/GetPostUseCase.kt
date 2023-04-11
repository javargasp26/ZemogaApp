package com.example.zemogaapp.domain.usecase

import com.example.zemogaapp.data.local.entity.toDatabase
import com.example.zemogaapp.data.repository.DataRepository
import com.example.zemogaapp.domain.model.Comment
import com.example.zemogaapp.domain.model.Post
import com.example.zemogaapp.domain.model.User
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val repository: DataRepository){

    suspend operator fun invoke(reload: Boolean): List<Post> {

        if (reload){
            repository.clearPost()
        }

        val postList = repository.getPostFromDataBase()

        val postListToReturn: MutableList<Post>
        val userListToReturn: MutableList<User>
        val commentListToReturn: MutableList<Comment>

        if (postList.isEmpty()){
            postListToReturn = repository.getPost().toMutableList()
            if (postListToReturn.isNotEmpty()){
                userListToReturn = repository.getUsers().toMutableList()
                if (userListToReturn.isNotEmpty()){
                    postListToReturn.forEach { post ->
                        userListToReturn.forEach { user ->
                            if (user.userId == post.userId){
                                post.userName = user.userName
                            }
                        }
                    }
                }
                commentListToReturn = repository.getComments().toMutableList()
                if (commentListToReturn.isNotEmpty()){
                    postListToReturn.forEach { post ->
                        commentListToReturn.forEach { comment ->
                            if (comment.postId == post.postId){
                                post.commentList.add(comment)
                            }
                        }
                    }
                }
            }
            repository.clearPost()
            repository.insertPost(postListToReturn.map { it.toDatabase() })
        }else{
            postListToReturn = postList.toMutableList()
        }
        return postListToReturn
    }

}