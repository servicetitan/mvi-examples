package com.servicetitan.mviexample.processors

import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.services.DalProvider
import com.servicetitan.mviexample.state.MovieState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieEventProcessor @Inject constructor(
    private val dalProvider: DalProvider
) : BaseProcessor<MovieEvent, MovieState>() {

    override fun processEvent(event: MovieEvent) {
        Timber.d(event.log())
        when (event) {
            is MovieEvent.Request -> {
                GlobalScope.launch {
                    stateDispatcher.onNext(MovieState.Loading)
                    dalProvider.stDalManager.searchMovies(event.payload.searchQuery.value).also {
                        stateDispatcher.onNext(MovieState.Received(it))
                    }
                }
            }
//            is MovieEvent.MovieDetails -> {
//                stateDispatcher.onNext(MovieState.NavigateMovieDetail(event.movieid))
//            }
        }
    }
}