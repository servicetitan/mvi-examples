package com.servicetitan.mviexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.servicetitan.mviexample.core.EventDispatcher
import com.servicetitan.mviexample.processors.MoviesReceivedProcessor
import com.servicetitan.mviexample.processors.MoviesRequestedProcessor
import com.servicetitan.mviexample.repositories.MovieRepository
import com.servicetitan.mviexample.ui.MovieSearch
import com.servicetitan.mviexample.ui.theme.MVIExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var moviesRequestedProcessor: MoviesRequestedProcessor
    @Inject
    lateinit var moviesReceivedProcessor: MoviesReceivedProcessor
    @Inject
    lateinit var movieRepository: MovieRepository
    @Inject
    lateinit var eventDispatcher: EventDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVIExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MovieSearch(movieRepository, eventDispatcher)
                }
            }
        }
    }
}

