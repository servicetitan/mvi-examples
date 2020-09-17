package com.example.dal.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dal.entities.Movie
import com.example.dal.entities.MovieDetail

@Database(entities = [Movie::class, MovieDetail::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class OmdbDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
}