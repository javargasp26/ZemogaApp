package com.example.zemogaapp.di

import android.content.Context
import androidx.room.Room
import com.example.zemogaapp.data.local.ProjectDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val PROJECT_DATABASE_NAME = "project_database"

    @Singleton
    @Provides
    fun provideRoom (@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ProjectDatabase::class.java, PROJECT_DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePostDao(db:ProjectDatabase) = db.getPostList()
}