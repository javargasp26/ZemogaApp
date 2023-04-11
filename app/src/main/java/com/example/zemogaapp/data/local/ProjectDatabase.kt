package com.example.zemogaapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zemogaapp.data.local.dao.PostDao
import com.example.zemogaapp.data.local.entity.PostEntity

@Database(entities = [PostEntity::class], version = 3)
@TypeConverters(Converters::class)

abstract class ProjectDatabase: RoomDatabase() {

    abstract fun getPostList(): PostDao

}