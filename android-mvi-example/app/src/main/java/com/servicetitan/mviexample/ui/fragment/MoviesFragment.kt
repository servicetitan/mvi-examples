package com.servicetitan.mviexample.ui.fragment

import android.view.View
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.state.MovieState
import com.servicetitan.mviexample.ui.view.MovieSearch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MovieEvent, MovieState>() {

    override fun composeView(): View =
        ComposeView(requireContext()).apply {
            setContent {
                MovieSearch(
                    eventProcessor.stateSource.collectAsState(initial = MovieState.None),
                    { searchMovies(it) }) { navigateToMovie(it) }
            }
        }

    private fun searchMovies(query: String) = emitEvent(MovieEvent.Requested(query))

    private fun navigateToMovie(imdbId: String) =
        findNavController().navigate(MoviesFragmentDirections.toMovieDetails(imdbId))
}