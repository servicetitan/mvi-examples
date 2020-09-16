package com.servicetitan.mviexample.ui.fragment

import android.view.View
import androidx.compose.runtime.State
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.state.MovieState
import com.servicetitan.mviexample.ui.view.MovieSearch
import com.servicetitan.mviexample.ui.view.MovieSearchProps
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MovieEvent, MovieState>(MovieState.Received(emptyList())) {
    override fun composeView(state: State<MovieState?>): View =
        ComposeView(requireContext()).apply {
            setContent {
                state.value?.let {
                    MovieSearch(mapStateToProps(it))
                }
            }
        }

    private fun searchMovies(searchQuery: String) = emitEvent(MovieEvent.Request(searchQuery))

    private fun navigateToMovie(imdbId: String) =
        findNavController().navigate(MoviesFragmentDirections.toMovieDetails(imdbId))

    private fun mapStateToProps(state: MovieState): MovieSearchProps =
        when(state) {
            is MovieState.Loading -> MovieSearchProps.Loading
            is MovieState.Received -> MovieSearchProps.Received(
                state.movies,
                { searchMovies(it) },
                { navigateToMovie(it) }
            )
        }
}