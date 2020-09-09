package com.servicetitan.mviexample.ui.view

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.state.MovieState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

open class BaseView(eventProcessor: MovieEventProcessor) {

    var viewState: MutableState<MovieState> = mutableStateOf(MovieState.None)
    private val disposable = CompositeDisposable()

    init {
        eventProcessor.stateListener
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Log.d("Movie View", "Failed to Process State") }
            .subscribe { viewState.value = it }
            .addTo(disposable)
    }

    fun dispose() {
        disposable.clear()
    }
}