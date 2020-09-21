package com.servicetitan.mviexample.events

import com.example.dal.entities.Movie


sealed class MovieEvent: BaseEvent() {
    override var eventType: String = "MovieEvent"

    class Requested(val searchQuery: String) : MovieEvent()
}