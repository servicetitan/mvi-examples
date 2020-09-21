package com.servicetitan.mviexample.events

sealed class MovieDetailEvent: BaseEvent() {
    override var eventType: String = "MovieDetailEvent"

    class Request(val movieId: String) : MovieDetailEvent()
}