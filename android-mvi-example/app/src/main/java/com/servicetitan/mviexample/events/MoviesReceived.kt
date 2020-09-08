package com.servicetitan.mviexample.events

import com.servicetitan.mviexample.core.Event
import com.servicetitan.mviexample.entities.Movie

class MoviesReceived(val payload: Payload) : Event() {
    class Payload(
        val movies: Iterable<Movie>
    )
}