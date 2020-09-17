package com.servicetitan.mviexample.ui.fragment

import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.state.MovieState
import com.servicetitan.mviexample.ui.view.MovieSearch
import com.servicetitan.mviexample.ui.view.MovieViewInitializer
import dagger.hilt.android.AndroidEntryPoint

interface MoviesDelegate {
    fun searchMovies(query: String)
    fun navigateToMovie(imdbId: String)
}

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MovieEvent, MovieState>(), MoviesDelegate {

    override val initialState: MovieState = MovieState.None

    override fun composeView(): View =
        ComposeView(requireContext()).apply {
            setContent {
                MovieSearch(MovieViewInitializer(viewState, this@MoviesFragment))
            }
        }

    override fun searchMovies(query: String) = emitEvent(MovieEvent.Request(query))

    override fun navigateToMovie(imdbId: String) =
        findNavController().navigate(MoviesFragmentDirections.toMovieDetails(imdbId))
}