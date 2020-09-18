package com.servicetitan.mviexample.processors

import com.servicetitan.mviexample.events.BaseEvent
import com.servicetitan.mviexample.state.BaseState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

abstract class BaseProcessor<E: BaseEvent, S: BaseState> {

    protected val eventDispatcher = BehaviorSubject.create<E>()
    protected val stateDispatcher = BehaviorSubject.create<S>()
    private val disposable = CompositeDisposable()

    val stateListener: Observable<S> = stateDispatcher.hide()

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
