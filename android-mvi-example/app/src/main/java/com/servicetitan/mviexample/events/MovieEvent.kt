package com.servicetitan.mviexample.events

import com.servicetitan.mviexample.entities.Movie

sealed class MovieEvent: BaseEvent() {
    override var eventType: String = "MovieEvent"

    class Request(val searchQuery: String) : MovieEvent()
    class RequestAPI(val searchQuery: String) : MovieEvent()
    class SaveDB(val movies: List<Movie>) : MovieEvent()
}