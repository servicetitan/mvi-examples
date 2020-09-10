package com.servicetitan.mviexample.processors

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

open class BaseProcessor<E, S> {

    val eventDispatcher = BehaviorSubject.create<E>()
    protected val stateDispatcher = BehaviorSubject.create<S>()
    val stateListener: Observable<S> = stateDispatcher.hide()

    protected val disposable = CompositeDisposable()

    fun dispose() {
        disposable.clear()
    }
}