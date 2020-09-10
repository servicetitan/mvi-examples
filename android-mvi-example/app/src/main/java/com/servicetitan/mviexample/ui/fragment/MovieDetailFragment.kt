package com.servicetitan.mviexample.ui.fragment

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.ui.view.MovieDetailView
import com.servicetitan.mviexample.ui.view.MoviesView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieEventProcessor>() {

    lateinit var view: MovieDetailView

    override fun composeView(): View =
        ComposeView(requireContext()).apply {
            setContent { /* TODO */ }
        }

    override fun onDestroy() {
        super.onDestroy()
        //view.dispose()
    }
}