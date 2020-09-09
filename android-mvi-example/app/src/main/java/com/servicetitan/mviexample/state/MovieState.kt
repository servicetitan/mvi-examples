package com.servicetitan.mviexample.state

import com.servicetitan.mviexample.entities.Movie

sealed class MovieState {
    object None: MovieState()
    object Loading: MovieState()
    class Received(val movies: List<Movie>): MovieState()
}