package com.servicetitan.mviexample.processors

import com.example.dal.manager.STDalManager
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.state.MovieState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieEventProcessor(private val stDalManager: STDalManager) : BaseProcessor<MovieEvent, MovieState>() {

    override fun processEvent(event: MovieEvent) {
        Timber.d(event.log())
        when (event) {
            is MovieEvent.Request -> {
                GlobalScope.launch {
                    stateDispatcher.onNext(MovieState.Loading)
                    stDalManager.searchMovies(event.searchQuery).also {
                        stateDispatcher.onNext(MovieState.Received(it))
                    }
                }
            }
        }
    }
}