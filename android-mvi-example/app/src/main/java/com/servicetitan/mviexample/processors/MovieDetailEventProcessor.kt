package com.servicetitan.mviexample.processors

import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.services.DalProvider
import com.servicetitan.mviexample.state.MovieDetailState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieDetailEventProcessor @Inject constructor(
    private val dalProvider: DalProvider
) : BaseProcessor<MovieDetailEvent, MovieDetailState>() {

    override fun processEvent(event: MovieDetailEvent) {
        Timber.d(event.log())
        when (event) {
            is MovieDetailEvent.Request -> {
                GlobalScope.launch {
                    stateDispatcher.onNext(MovieDetailState.Loading)
                    dalProvider.stDalManager.movieDetail(event.movieId).also {
                        stateDispatcher.onNext(MovieDetailState.Received(it))
                    }
                }
            }
        }
    }
}