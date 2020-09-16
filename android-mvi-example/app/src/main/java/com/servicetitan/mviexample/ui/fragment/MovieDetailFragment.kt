package com.servicetitan.mviexample.ui.fragment

import android.view.View
import androidx.compose.runtime.State
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.navArgs
import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.processors.MovieDetailEventProcessor
import com.servicetitan.mviexample.state.MovieDetailState
import com.servicetitan.mviexample.ui.view.MovieDetailView
import com.servicetitan.mviexample.ui.view.MovieDetailViewProps
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailEvent, MovieDetailState>(null) {

    private val args by navArgs<MovieDetailFragmentArgs>()

    private fun requestMovieDetails() = emitEvent(MovieDetailEvent.Request(args.movieId))

    override fun composeView(state: State<MovieDetailState?>): View {
        requestMovieDetails()
        return ComposeView(requireContext()).apply {
            setContent {
                state.value?.let {
                    MovieDetailView(mapStateToProps(it))
                }
            }
        }
    }

    private fun mapStateToProps(state: MovieDetailState): MovieDetailViewProps =
        when(state) {
            is MovieDetailState.Loading -> MovieDetailViewProps.Loading
            is MovieDetailState.Error -> MovieDetailViewProps.Error(state.error) { requestMovieDetails() }
            is MovieDetailState.Received -> MovieDetailViewProps.Received(state.movieDetail)
        }
}