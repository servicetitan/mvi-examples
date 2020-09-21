package com.servicetitan.mviexample.events

sealed class MovieDetailEvent: BaseEvent() {
    override var eventType: String = "MovieDetailEvent"

    class Requested(val movieId: String) : MovieDetailEvent()
}