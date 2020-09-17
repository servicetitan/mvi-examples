package com.example.dal.api

import com.example.dal.entities.MovieDetail
import com.example.dal.entities.SearchMovie
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

private const val BASE_URL = "https://www.omdbapi.com/"
private const val API_KEY = "eff188ce"

interface OmdbApi {

    @GET
    suspend fun searchMovies(
        @Url url: String = BASE_URL,
        @Query("s") search: String,
        @Query("apikey") apiKey: String = API_KEY
    ): SearchMovie

    @GET
    suspend fun movieDetail(
        @Url url: String = BASE_URL,
        @Query("i") movieId: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieDetail
}