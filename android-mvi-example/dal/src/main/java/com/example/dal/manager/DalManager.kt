package com.example.dal.manager

import android.content.Context
import androidx.room.Room
import com.example.dal.api.OmdbApi
import com.example.dal.db.OmdbDatabase
import com.example.dal.entities.Movie
import com.example.dal.entities.MovieDetail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.omdbapi.com/"
private const val DATABASE_NAME = "movie-database"

class DalManager : STDalManager {

    lateinit var omdbApi: OmdbApi
    lateinit var omdbDatabase: OmdbDatabase

    fun init(context: Context) {
        omdbApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbApi::class.java)

        omdbDatabase = Room.databaseBuilder(context, OmdbDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    override suspend fun searchMovies(query: String): List<Movie> =
        omdbDatabase.movieDao().findByQuery("%${query}%").let {
            if (it.isEmpty()) {
                omdbApi.searchMovies(search = query).search.also { omdbDatabase.movieDao().insert(it) }
            } else { it }
        }

    override suspend fun movieDetail(movieId: String): MovieDetail =
        omdbDatabase.movieDetailDao().findById(movieId).let {
            it ?: omdbApi.movieDetail(movieId = movieId).also { omdbDatabase.movieDetailDao().insert(it) }
        }
}