package com.servicetitan.mviexample.services.db

import androidx.room.*
import com.servicetitan.mviexample.entities.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Movie>)

    @Delete
    suspend fun delete(item: Movie): Int

    @Query("SELECT * FROM Movie")
    suspend fun allMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE imdbId LIKE :id LIMIT 1")
    suspend fun findById(id: String): Movie

    @Query("SELECT * FROM Movie WHERE title LIKE :query")
    suspend fun findByQuery(query: String): List<Movie>
}

