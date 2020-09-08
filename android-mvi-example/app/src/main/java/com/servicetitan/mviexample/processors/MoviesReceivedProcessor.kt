package com.servicetitan.mviexample.processors
import com.servicetitan.mviexample.core.EventDispatcher
import com.servicetitan.mviexample.events.MoviesReceived
import com.servicetitan.mviexample.repositories.MovieRepository
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesReceivedProcessor @Inject constructor(
    val movieRepository: MovieRepository,
    val eventDispatcher: EventDispatcher) {
    val eventSubscription: Disposable

    init {
        eventSubscription = eventDispatcher.eventStream
            .filter { it is MoviesReceived }
            .subscribeBy { GlobalScope.launch { onNext(it as MoviesReceived) }}
    }

    private suspend fun onNext(event: MoviesReceived) {
        movieRepository.setMovies(event.payload.movies.toList())
    }
}