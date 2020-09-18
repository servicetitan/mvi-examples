package com.example.dal.api

import com.example.dal.entities.Movie
import com.example.dal.entities.MovieDetail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.omdbapi.com/"
private const val API_KEY = "eff188ce"

class OmdbRetrofitApi {

    private val api: OmdbApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbApi::class.java)
    }

    suspend fun search(searchQuery: String): List<Movie> =
        api.searchMovies(BASE_URL, searchQuery, API_KEY).search

    suspend fun movieDetail(movieId: String): MovieDetail =
        api.movieDetail(BASE_URL, movieId, API_KEY)
}