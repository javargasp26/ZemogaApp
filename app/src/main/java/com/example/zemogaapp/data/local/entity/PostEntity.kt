package com.example.zemogaapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zemogaapp.domain.model.Comment
import com.example.zemogaapp.domain.model.Post

@Entity(tableName = "post_table")
data class PostEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int =0,
    @ColumnInfo(name = "postId") val postId:Int,
    @ColumnInfo(name = "postTitle") val postTitle: String,
    @ColumnInfo(name = "postDescription") val postDescription: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean,
    @ColumnInfo(name = "commentList") val commentList: List<Comment>,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "userName") val userName: String
)

fun Post.toDatabase() = PostEntity(
    postId = postId,
    postTitle = postTitle,
    postDescription = postDescription,
    isFavorite = isFavorite,
    commentList = commentList,
    userId = userId,
    userName = userName
)