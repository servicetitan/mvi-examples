package com.servicetitan.mviexample.services.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.servicetitan.mviexample.entities.Movie
import com.servicetitan.mviexample.entities.MovieDetail

@Database(entities = [Movie::class, MovieDetail::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
}