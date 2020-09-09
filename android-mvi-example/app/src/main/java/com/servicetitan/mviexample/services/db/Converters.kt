package com.servicetitan.mviexample.services.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.servicetitan.mviexample.entities.Rating


private const val COMMA = ","

class Converters {

    @TypeConverter
    fun stringToIntList(value: String): List<Int> = value.split(COMMA).map { it.toInt() }

    @TypeConverter
    fun intListToString(values: List<Int>): String = values.joinToString(COMMA)

    @TypeConverter
    fun stringToList(value: String): List<String> = value.split(COMMA)


    @TypeConverter
    fun listToString(values: List<String>): String = values.joinToString(COMMA)

    @TypeConverter
    fun stringToRatings(value: String): List<Rating> =
        Gson().fromJson<List<Rating>>(value, object : TypeToken<List<Rating>>() {}.type)

    @TypeConverter
    fun ratingToString(ratings: List<Rating>): String = Gson().toJson(ratings)
}