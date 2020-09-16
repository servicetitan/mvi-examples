package com.servicetitan.mviexample.processors

import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.state.MovieDetailState
import com.servicetitan.mviexample.state.MovieState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProcessorProvider {
    @Binds
    abstract fun getMovieDetailEventProcessor(instance: MovieDetailEventProcessor):
            BaseProcessor<MovieDetailEvent, MovieDetailState>

    @Binds
    abstract fun getMovieEventProcessor(instance: MovieEventProcessor):
            BaseProcessor<MovieEvent, MovieState>
}