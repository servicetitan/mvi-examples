package com.servicetitan.mviexample.ui.fragment

import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.navArgs
import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.state.MovieDetailState
import com.servicetitan.mviexample.ui.view.MovieDetailView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailEvent, MovieDetailState>() {

    private val args by navArgs<MovieDetailFragmentArgs>()

    override val initialState: MovieDetailState = MovieDetailState.None

    override fun composeView(): View {
        requestMovieDetails()
        return ComposeView(requireContext()).apply {
            setContent {
                MovieDetailView(viewState) { requestMovieDetails() }
            }
        }
    }

    private fun requestMovieDetails() = emitEvent(MovieDetailEvent.Requested(args.movieId))
}