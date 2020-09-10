package com.servicetitan.mviexample.ui.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.servicetitan.mviexample.processors.BaseProcessor
import com.servicetitan.mviexample.state.BaseState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

open class BaseView<E, S : BaseState>(eventProcessor: BaseProcessor<E, S>) {

    var viewState: MutableState<BaseState> = mutableStateOf(BaseState.None)
    private val disposable = CompositeDisposable()

    init {
        eventProcessor.stateListener
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Timber.d("Failed to Process State") }
            .subscribe { viewState.value = it.also { Timber.d(it.log()) } }
            .addTo(disposable)
    }

    fun dispose() {
        disposable.clear()
    }
}