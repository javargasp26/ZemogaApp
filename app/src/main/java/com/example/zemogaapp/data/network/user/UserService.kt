package com.example.zemogaapp.data.network.user

import com.example.zemogaapp.data.network.post.PostApi
import com.example.zemogaapp.data.network.post.PostResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(private val userApi: UserApi) {
    suspend fun getAllUsers(): List<UserResponseModel> {
        return withContext(Dispatchers.IO) {

            val userResponse = userApi.getUsers()
            if (userResponse.isSuccessful){
                userResponse.body()!!
            }else{
                listOf()
            }
        }
    }
}