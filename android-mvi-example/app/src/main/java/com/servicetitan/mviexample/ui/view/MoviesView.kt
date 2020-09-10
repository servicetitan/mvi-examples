package com.servicetitan.mviexample.ui.view

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.state.MovieState
import com.servicetitan.mviexample.ui.theme.MVIExampleTheme

data class Payload(val searchQuery: MutableState<String> = mutableStateOf(""))

class MoviesView(private val eventProcessor: MovieEventProcessor) : BaseView(eventProcessor) {

    private var payload = Payload()

    @Composable
    fun MovieSearch() {
        MVIExampleTheme {
            Surface(color = MaterialTheme.colors.background) {
                Column {
                    TextField(
                        value = payload.searchQuery.value,
                        onValueChange = { payload.searchQuery.value = it },
                        label = { Text("Search for movies") }
                    )
                    Button(
                        onClick = { eventProcessor.eventDispatcher.onNext(MovieEvent.Request(payload)) },
                        content = { Text(text = "Search") }
                    )
                    when (viewState.value) {
                        is MovieState.Loading -> Loading()
                        is MovieState.Received -> Movie()
                        else -> {}
                    }
                }
            }
        }
    }

    @Composable
    fun Loading() {
        CircularProgressIndicator(progress = 0.5f)
    }

    @Composable
    fun Movie() {
        LazyColumnFor(items = (viewState.value as MovieState.Received).movies) {
            Text(it.title)
        }
    }
}

