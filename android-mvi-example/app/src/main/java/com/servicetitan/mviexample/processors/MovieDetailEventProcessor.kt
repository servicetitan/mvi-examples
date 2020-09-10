package com.servicetitan.mviexample.processors

import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.services.api.OmdbRetrofitApi
import com.servicetitan.mviexample.services.db.OmdbDatabase
import com.servicetitan.mviexample.state.MovieDetailState
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieDetailEventProcessor @Inject constructor(
    private var omdbRetrofitApi: OmdbRetrofitApi,
    private var omdbDatabase: OmdbDatabase
): BaseProcessor<MovieDetailEvent, MovieDetailState>() {

    init {
        eventDispatcher
            .doOnError { Timber.d("Movie Detail Event Process Error $it") }
            .subscribe { processEvent(it) }.addTo(disposable)
    }

    private fun processEvent(event: MovieDetailEvent) {
        Timber.d(event.log())
        when (event) {
            is MovieDetailEvent.Request -> {
                GlobalScope.launch {
                    stateDispatcher.onNext(MovieDetailState.Loading)
                    val movie = omdbDatabase.provideDatabase().movieDetailDao()
                        .findById(event.movieId)
                    if (movie == null) {
                        eventDispatcher.onNext(MovieDetailEvent.RequestAPI(event.movieId))
                    } else {
                        stateDispatcher.onNext(MovieDetailState.Received(movie))
                    }
                }
            }
            is MovieDetailEvent.RequestAPI -> {
                GlobalScope.launch {
                    runCatching {
                        omdbRetrofitApi.movieDetail(event.movieId)
                    }.onFailure {
                        stateDispatcher.onNext(MovieDetailState.Error("Couldn't find the movie"))
                    }.onSuccess {
                        eventDispatcher.onNext(MovieDetailEvent.SaveDB(it))
                    }
                }
            }
            is MovieDetailEvent.SaveDB -> {
                GlobalScope.launch {
                    omdbDatabase.provideDatabase().movieDetailDao().insert(event.movie)
                    stateDispatcher.onNext(MovieDetailState.Received(event.movie))
                }
            }
        }
    }
}