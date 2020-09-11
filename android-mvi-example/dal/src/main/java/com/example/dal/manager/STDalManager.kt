package com.example.dal.manager

import com.example.dal.entities.Movie
import com.example.dal.entities.MovieDetail

interface STDalManager {

    suspend fun searchMovies(query: String): List<Movie>

    suspend fun movieDetail(movieId: String): MovieDetail
}