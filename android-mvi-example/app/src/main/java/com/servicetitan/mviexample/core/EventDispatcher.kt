package com.servicetitan.mviexample.core

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventDispatcher @Inject constructor() {
    private val eventSubject: PublishSubject<Event> = PublishSubject.create()
    val eventStream get(): Observable<Event> = eventSubject

    fun dispatch(event: Event) {
        eventSubject.onNext(event)
    }
}
