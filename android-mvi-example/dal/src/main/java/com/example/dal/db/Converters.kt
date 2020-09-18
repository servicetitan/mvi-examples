package com.example.dal.db

import androidx.room.TypeConverter
import com.example.dal.entities.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun stringToRatings(value: String): List<Rating> =
        Gson().fromJson(value, object : TypeToken<List<Rating>>() {}.type)

    @TypeConverter
    fun ratingToString(ratings: List<Rating>): String = Gson().toJson(ratings)
}