package com.servicetitan.mviexample.processors

import com.servicetitan.mviexample.events.BaseEvent
import com.servicetitan.mviexample.state.BaseState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

abstract class BaseProcessor<E: BaseEvent, S: BaseState> {

    private val eventDispatcher = BehaviorSubject.create<E>()
    private val stateDispatcher = BehaviorSubject.create<S>()
    private val disposable = CompositeDisposable()

    val stateSource: Observable<S> = stateDispatcher.hide()

    init {
        eventDispatcher
            .doOnNext { Timber.d(it.log()) }
            .doOnError { Timber.d("Event Process Error $it") }
            .subscribe { processEvent(it) }
            .addTo(disposable)
    }

    fun dispose() {
        disposable.clear()
    }

    fun handleEvent(event: E) = eventDispatcher.onNext(event)

    protected abstract fun processEvent(event: E)

    protected fun emitState(state: S) = stateDispatcher.onNext(state)
}
