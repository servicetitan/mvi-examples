package com.servicetitan.mviexample.services.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.servicetitan.mviexample.entities.Movie

@Database(entities = [Movie::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}