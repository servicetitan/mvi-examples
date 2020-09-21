package com.servicetitan.mviexample.processors

import com.example.dal.manager.STDalManager
import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.state.MovieDetailState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieDetailEventProcessor(private val stDalManager: STDalManager) :
    BaseProcessor<MovieDetailEvent, MovieDetailState>() {

    override fun processEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.Requested -> {
                GlobalScope.launch {
                    emitState(MovieDetailState.Loading)
                    stDalManager.movieDetail(event.movieId).also {
                        emitState(MovieDetailState.Received(it))
                    }
                }
            }
        }
    }
}