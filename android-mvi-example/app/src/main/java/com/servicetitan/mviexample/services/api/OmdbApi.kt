package com.servicetitan.mviexample.services.api

import com.servicetitan.mviexample.entities.MovieDetail
import com.servicetitan.mviexample.entities.SearchMovie
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface OmdbApi {

    @GET
    suspend fun searchMovies(
        @Url url: String,
        @Query("s") search: String,
        @Query("apikey") apiKey: String
    ): SearchMovie

    @GET
    suspend fun movieDetail(
        @Url url: String,
        @Query("i") movieId: String,
        @Query("apikey") apiKey: String
    ): MovieDetail
}