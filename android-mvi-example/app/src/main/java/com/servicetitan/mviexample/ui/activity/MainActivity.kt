package com.servicetitan.mviexample.ui.activity

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.ui.view.MoviesView
import com.servicetitan.mviexample.ui.theme.MVIExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MovieEventProcessor>() {

    lateinit var view: MoviesView

    override fun onViewCreated() {
        view = MoviesView(eventProcessor)
        setContent {
            MVIExampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    view.MovieSearch()
                }
            }
        }
    }
}

