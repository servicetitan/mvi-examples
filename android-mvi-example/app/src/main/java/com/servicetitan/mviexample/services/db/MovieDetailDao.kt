package com.servicetitan.mviexample.services.db

import androidx.room.*
import com.servicetitan.mviexample.entities.MovieDetail

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MovieDetail)

    @Delete
    suspend fun delete(item: MovieDetail): Int

    @Query("SELECT * FROM MovieDetail WHERE imdbId LIKE :id LIMIT 1")
    suspend fun findById(id: String): MovieDetail?
}