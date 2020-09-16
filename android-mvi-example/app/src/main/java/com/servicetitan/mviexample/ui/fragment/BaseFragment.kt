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

abstract class BaseFragment<E: BaseEvent, S: BaseState>(initialState: S?): Fragment() {

    @Inject
    lateinit var eventProcessor: BaseProcessor<@JvmSuppressWildcards E, @JvmSuppressWildcards S>
    private val state: MutableState<S?> = mutableStateOf(initialState)
    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        eventProcessor.stateListener
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Timber.d("Failed to Process State") }
            .subscribe { state.value = it.also { Timber.d(it.log()) } }
            .addTo(disposable)
        return composeView(state)
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        eventProcessor.dispose()
    }

    abstract fun composeView(state: State<S?>): View

    protected fun emitEvent(event: E) = eventProcessor.processEvent(event)
}