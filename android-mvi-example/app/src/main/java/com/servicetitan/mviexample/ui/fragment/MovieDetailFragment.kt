package com.servicetitan.mviexample.ui.fragment

import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.navArgs
import com.servicetitan.mviexample.processors.MovieDetailEventProcessor
import com.servicetitan.mviexample.ui.view.MovieDetailView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailEventProcessor>() {

    val args by navArgs<MovieDetailFragmentArgs>()

    lateinit var view: MovieDetailView

    override fun composeView(): View =
        ComposeView(requireContext()).apply {
            view = MovieDetailView(eventProcessor)
            setContent { view.MovieDetailView(args.movieId) }
        }

    override fun onDestroy() {
        super.onDestroy()
        //view.dispose()
    }
}