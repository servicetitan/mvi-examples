package com.servicetitan.mviexample.processors

import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.services.api.OmdbRetrofitApi
import com.servicetitan.mviexample.services.db.OmdbDatabase
import com.servicetitan.mviexample.state.MovieState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieEventProcessor @Inject constructor(
    private var omdbRetrofitApi: OmdbRetrofitApi,
    private var omdbDatabase: OmdbDatabase
) : BaseProcessor<MovieEvent, MovieState>() {

    override fun processEvent(event: MovieEvent) {
        Timber.d(event.log())
        when (event) {
            is MovieEvent.Request -> {
                GlobalScope.launch {
                    stateDispatcher.onNext(MovieState.Loading)
                    val movies = omdbDatabase.provideDatabase().movieDao()
                        .findByQuery("%${event.payload.searchQuery.value}%")
                    if (movies.isEmpty()) {
                        eventDispatcher.onNext(MovieEvent.RequestAPI(event.payload))
                    } else {
                        stateDispatcher.onNext(MovieState.Received(movies))
                    }
                }
            }
            is MovieEvent.RequestAPI -> {
                GlobalScope.launch {
                    runCatching {
                        omdbRetrofitApi.search(event.payload.searchQuery.value)
                    }.onFailure {
                        stateDispatcher.onNext(MovieState.Received(emptyList()))
                    }.onSuccess {
                        eventDispatcher.onNext(MovieEvent.SaveDB(it))
                    }
                }
            }
            is MovieEvent.SaveDB -> {
                GlobalScope.launch {
                    omdbDatabase.provideDatabase().movieDao().insert(event.movies)
                    stateDispatcher.onNext(MovieState.Received(event.movies))
                }
            }
//            is MovieEvent.MovieDetails -> {
//                stateDispatcher.onNext(MovieState.NavigateMovieDetail(event.movieid))
//            }
        }
    }
}