package com.servicetitan.mviexample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.Fragment
import com.servicetitan.mviexample.events.BaseEvent
import com.servicetitan.mviexample.processors.BaseProcessor
import com.servicetitan.mviexample.state.BaseState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<E: BaseEvent, S: BaseState>: Fragment() {

    @Inject
    lateinit var eventProcessor: BaseProcessor<E, S>
    private val state: MutableState<S> = mutableStateOf(initialState)
    protected val viewState: State<S> = state
    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        composeView().also {
            eventProcessor.stateSource
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { Timber.d("Failed to Process State") }
                .subscribe { state.value = it.also { Timber.d(it.log()) } }
                .addTo(disposable)
        }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        eventProcessor.dispose()
    }

    abstract fun composeView(): View
    abstract val initialState: S
    protected fun emitEvent(event: E) = eventProcessor.handleEvent(event)
}