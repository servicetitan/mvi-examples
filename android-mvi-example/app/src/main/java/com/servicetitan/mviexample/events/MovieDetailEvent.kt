package com.servicetitan.mviexample.events

import com.servicetitan.mviexample.entities.MovieDetail

sealed class MovieDetailEvent: BaseEvent() {
    class Request(val movieId: String) : MovieDetailEvent()
    class RequestAPI(val movieId: String) : MovieDetailEvent()
    class SaveDB(val movie: MovieDetail) : MovieDetailEvent()
}