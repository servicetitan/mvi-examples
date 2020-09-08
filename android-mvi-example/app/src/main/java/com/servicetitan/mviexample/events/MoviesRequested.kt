package com.servicetitan.mviexample.events

import com.servicetitan.mviexample.core.Event

class MoviesRequested(val payload: Payload) : Event() {
    class Payload(
        val searchString: String
    )
}