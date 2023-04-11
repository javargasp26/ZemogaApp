package com.example.zemogaapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zemogaapp.data.local.entity.PostEntity
import com.example.zemogaapp.domain.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM post_table ORDER BY postId ASC")
    suspend fun getAllPost(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPost(postList: List<PostEntity>)

    @Query("SELECT * FROM post_table WHERE postId like :postId ")
    suspend fun getPost(postId: Int): Post

    @Query("UPDATE post_table SET isFavorite=:favorite where postId like :postId")
    fun updateFavoriteById(postId: Int, favorite:Boolean)

    @Query("SELECT * FROM post_table where isFavorite= 1 ORDER BY postId ASC ")
    suspend fun getFavorites():List<PostEntity>

    @Query("DELETE FROM post_table")
    suspend fun clearPost()
}