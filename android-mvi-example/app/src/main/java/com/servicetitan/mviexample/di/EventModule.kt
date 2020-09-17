package com.servicetitan.mviexample.di

import android.app.Application
import com.example.dal.STDal
import com.example.dal.manager.STDalManager
import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.processors.BaseProcessor
import com.servicetitan.mviexample.processors.MovieDetailEventProcessor
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.state.MovieDetailState
import com.servicetitan.mviexample.state.MovieState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class EventModule {

    @Provides
    fun provideDalProvider(application: Application): STDalManager = STDal.init(application)

    @Provides
    fun provideMovieEventProcessor(stDalManager: STDalManager): BaseProcessor<MovieEvent, MovieState> =
        MovieEventProcessor(stDalManager)

    @Provides
    fun provideMovieDetailEventProcessor(stDalManager: STDalManager): BaseProcessor<MovieDetailEvent, MovieDetailState> =
        MovieDetailEventProcessor(stDalManager)
}