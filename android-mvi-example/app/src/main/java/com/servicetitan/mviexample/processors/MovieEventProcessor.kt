package com.servicetitan.mviexample.processors

import com.example.dal.manager.STDalManager
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.state.MovieState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieEventProcessor(private val stDalManager: STDalManager) : BaseProcessor<MovieEvent, MovieState>() {

    override fun processEvent(event: MovieEvent) {
        when (event) {
            is MovieEvent.Requested -> {
                GlobalScope.launch {
                    emitState(MovieState.Loading)
                    stDalManager.searchMovies(event.searchQuery).also {
                        emitState(MovieState.Received(it))
                    }
                }
            }
        }
    }
}