package com.servicetitan.mviexample.state

import com.example.dal.entities.Movie


sealed class MovieState: BaseState() {
    override var stateType: String = "MovieState"

    object Loading: MovieState()
    class Received(val movies: List<Movie>): MovieState()
    //class NavigateMovieDetail(val movieId: String): MovieState()
}