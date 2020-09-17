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
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class EventModule {

    @Provides
    @Singleton
    fun provideDalProvider(application: Application): STDalManager = STDal.init(application)

    @Provides
    @Singleton
    fun provideMovieEventProcessor(stDalManager: STDalManager): BaseProcessor<MovieEvent, MovieState> =
        MovieEventProcessor(stDalManager)

    @Provides
    @Singleton
    fun provideMovieDetailEventProcessor(stDalManager: STDalManager): BaseProcessor<MovieDetailEvent, MovieDetailState> =
        MovieDetailEventProcessor(stDalManager)
}