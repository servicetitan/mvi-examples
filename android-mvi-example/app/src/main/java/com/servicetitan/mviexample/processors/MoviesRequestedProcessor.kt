package com.servicetitan.mviexample.processors
import com.servicetitan.mviexample.core.EventDispatcher
import com.servicetitan.mviexample.events.MoviesReceived
import com.servicetitan.mviexample.events.MoviesRequested
import com.servicetitan.mviexample.repositories.MovieRepository
import com.servicetitan.mviexample.services.OmdbApi
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
class MoviesRequestedProcessor @Inject constructor(
    val eventDispatcher: EventDispatcher,
    val omdbApi: OmdbApi
) {
    val eventSubscription: Disposable

    init {
        eventSubscription = eventDispatcher.eventStream
            .filter { it is MoviesRequested }
            .subscribeBy { GlobalScope.launch { onNext(it as MoviesRequested) }}
    }

    private suspend fun onNext(event: MoviesRequested) {
        val movies = omdbApi.search(event.payload.searchString)
        eventDispatcher.dispatch(MoviesReceived(MoviesReceived.Payload(movies)))
    }
}