package com.servicetitan.mviexample.events

import com.servicetitan.mviexample.entities.Movie
import com.servicetitan.mviexample.ui.view.Payload

sealed class MovieEvent: BaseEvent() {
    class Request(val payload: Payload) : MovieEvent()
    class RequestAPI(val payload: Payload) : MovieEvent()
    class SaveDB(val movies: List<Movie>) : MovieEvent()

    //class MovieDetails(val movieid: String): MovieEvent()
}