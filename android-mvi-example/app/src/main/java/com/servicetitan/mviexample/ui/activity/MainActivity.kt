package com.servicetitan.mviexample.ui.activity

import androidx.compose.ui.platform.setContent
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.ui.view.MoviesView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MovieEventProcessor>() {

    lateinit var view: MoviesView

    override fun onViewCreated() {
        view = MoviesView(eventProcessor)
        setContent { view.MovieSearch() }
    }

    override fun onDestroy() {
        super.onDestroy()
        view.dispose()
    }
}

