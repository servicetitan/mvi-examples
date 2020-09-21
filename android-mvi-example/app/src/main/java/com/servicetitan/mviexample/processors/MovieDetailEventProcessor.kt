package com.servicetitan.mviexample.processors

import com.example.dal.manager.STDalManager
import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.state.MovieDetailState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailEventProcessor(private val stDalManager: STDalManager) :
    BaseProcessor<MovieDetailEvent, MovieDetailState>() {

    override fun processEvent(event: MovieDetailEvent) {
        Timber.d(event.log())
        when (event) {
            is MovieDetailEvent.Request -> {
                GlobalScope.launch {
                    stateDispatcher.onNext(MovieDetailState.Loading)
                    stDalManager.movieDetail(event.movieId).also {
                        stateDispatcher.onNext(MovieDetailState.Received(it))
                    }
                }
            }
        }
    }
}