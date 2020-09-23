package com.servicetitan.mviexample.processors

import com.servicetitan.mviexample.events.BaseEvent
import com.servicetitan.mviexample.state.BaseState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
abstract class BaseProcessor<E: BaseEvent, S: BaseState> {

    private val eventDispatcher = ConflatedBroadcastChannel<E>()
    private val stateDispatcher = ConflatedBroadcastChannel<S>()
    val stateSource: Flow<S> = stateDispatcher.asFlow()

    init {
        GlobalScope.launch { eventDispatcher.consumeEach { processEvent(it) } }
    }

    fun handleEvent(event: E) {
        GlobalScope.launch { eventDispatcher.send(event).also { Timber.d(event.log()) } }
    }

    protected fun emitState(state: S) {
        GlobalScope.launch { stateDispatcher.send(state).also { Timber.d(state.log()) } }
    }

    protected abstract fun processEvent(event: E)
}
