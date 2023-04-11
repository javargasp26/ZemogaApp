package com.example.zemogaapp.data.local

import androidx.room.TypeConverter
import com.example.zemogaapp.domain.model.Comment
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<Comment>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<Comment>::class.java).toList()
}