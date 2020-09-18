package com.servicetitan.mviexample.state

import com.example.dal.entities.MovieDetail


sealed class MovieDetailState: BaseState() {
    override var stateType: String = "MovieDetailState"
    object None: MovieDetailState()
    object Loading: MovieDetailState()
    class Received(val movieDetail: MovieDetail): MovieDetailState()
    class Error(val error: String): MovieDetailState()
}