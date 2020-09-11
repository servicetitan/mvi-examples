package com.servicetitan.mviexample.events

import com.servicetitan.mviexample.ui.view.Payload

sealed class MovieEvent: BaseEvent() {
    override var eventType: String = "MovieEvent"

    class Request(val payload: Payload) : MovieEvent()
    //class MovieDetails(val movieid: String): MovieEvent()
}