package com.servicetitan.mviexample.processors

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

abstract class BaseProcessor<E, S> {

    val eventDispatcher = BehaviorSubject.create<E>()
    protected val stateDispatcher = BehaviorSubject.create<S>()
    val stateListener: Observable<S> = stateDispatcher.hide()

    protected val disposable = CompositeDisposable()

    init {
        eventDispatcher
            .doOnError { Timber.d("Event Process Error $it") }
            .subscribe { processEvent(it) }.addTo(disposable)
    }

    fun dispose() {
        disposable.clear()
    }

    abstract fun processEvent(event: E)
}