package com.servicetitan.mviexample.processors

import android.util.Log
import com.servicetitan.mviexample.events.MovieEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

private const val TAG = "BaseEventProcessor"

open class BaseProcessor<E, S> {

    val eventDispatcher = PublishSubject.create<E>()
    protected val stateDispatcher = PublishSubject.create<S>()
    val stateListener: Observable<S> = stateDispatcher.hide()

    protected val disposable = CompositeDisposable()

    protected fun logEvent(event: MovieEvent) {
        Log.d(TAG, "[${event.id} - ${event.timestamp}] -> ${event::class.java.simpleName}")
    }

    fun dispose() {
        disposable.clear()
    }
}