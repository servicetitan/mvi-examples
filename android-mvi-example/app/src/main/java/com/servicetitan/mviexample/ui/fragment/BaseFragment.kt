package com.servicetitan.mviexample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.servicetitan.mviexample.events.BaseEvent
import com.servicetitan.mviexample.processors.BaseProcessor
import com.servicetitan.mviexample.state.BaseState
import javax.inject.Inject

abstract class BaseFragment<E: BaseEvent, S: BaseState>: Fragment() {

    @Inject
    lateinit var eventProcessor: BaseProcessor<E, S>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        composeView()

    abstract fun composeView(): View
    protected fun emitEvent(event: E) = eventProcessor.handleEvent(event)
}