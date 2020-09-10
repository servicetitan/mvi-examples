package com.servicetitan.mviexample.ui.fragment

import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.ui.view.MoviesView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MovieEventProcessor>() {

    lateinit var view: MoviesView

    override fun composeView(): View =
        ComposeView(requireContext()).apply {
            view = MoviesView(eventProcessor)
            setContent { view.MovieSearch(findNavController()) }
        }

    override fun onDestroy() {
        super.onDestroy()
        view.dispose()
    }
}