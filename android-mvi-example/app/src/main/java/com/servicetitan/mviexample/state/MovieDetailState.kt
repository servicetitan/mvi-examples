package com.servicetitan.mviexample.state

import com.servicetitan.mviexample.entities.MovieDetail

sealed class MovieDetailState: BaseState() {
    object Loading: MovieDetailState()
    class Received(val movieDetail: MovieDetail): MovieDetailState()
    class Error(val error: String): MovieDetailState()
}