package com.servicetitan.mviexample.services.db

import android.app.Application
import android.content.Context
import androidx.room.Room
import javax.inject.Inject
import javax.inject.Singleton

private const val DATABASE_NAME = "movie-database"

@Singleton
class OmdbDatabase @Inject constructor(private val application: Application) {

    fun provideDatabase() =
        Room.databaseBuilder(application, MovieDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
}