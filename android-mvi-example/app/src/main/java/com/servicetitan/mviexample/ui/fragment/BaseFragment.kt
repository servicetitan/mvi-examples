package com.servicetitan.mviexample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.servicetitan.mviexample.processors.BaseProcessor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<T: BaseProcessor<*, *>>: Fragment() {

    @Inject
    lateinit var eventProcessor: T

    protected val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        composeView()

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        eventProcessor.dispose()
    }

    abstract fun composeView(): View
}