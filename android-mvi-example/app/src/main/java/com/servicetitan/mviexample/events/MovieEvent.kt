package com.servicetitan.mviexample.events

import com.servicetitan.mviexample.entities.Movie
import com.servicetitan.mviexample.ui.view.Payload
import java.util.*

sealed class MovieEvent {
    val id: UUID = UUID.randomUUID()
    val timestamp: Date = Date()

    class Request(val payload: Payload) : MovieEvent()
    class RequestAPI(val payload: Payload) : MovieEvent()
    class SaveDB(val movies: List<Movie>) : MovieEvent()
}