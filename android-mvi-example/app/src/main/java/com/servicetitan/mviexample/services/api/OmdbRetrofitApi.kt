package com.servicetitan.mviexample.services.api

import com.servicetitan.mviexample.entities.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://www.omdbapi.com"
private const val API_KEY = "eff188ce"

@Singleton
class OmdbRetrofitApi @Inject constructor() {

    private val api: OmdbApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbApi::class.java)
    }

    suspend fun search(searchQuery: String): List<Movie> =
        api.searchMovies(BASE_URL, searchQuery, API_KEY).search
}