package com.servicetitan.mviexample.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava2.subscribeAsState
import com.servicetitan.mviexample.core.EventDispatcher
import com.servicetitan.mviexample.entities.Movie
import com.servicetitan.mviexample.events.MoviesRequested
import com.servicetitan.mviexample.repositories.MovieRepository

@Composable
fun MovieSearch(movieRepository: MovieRepository, eventDispatcher: EventDispatcher) {
    val movies: List<Movie> by movieRepository.movies.subscribeAsState(emptyList())
    val (searchString, setSearchString) = remember { mutableStateOf("") }

    Column {
        TextField(
            value = searchString,
            onValueChange = { setSearchString(it) },
            label = { Text("Search for movies") }
        )
        Button(
            onClick = { eventDispatcher.dispatch(MoviesRequested(MoviesRequested.Payload(searchString))) },
            content = { Text(text = "Search") }
        )
        LazyColumnFor(items = movies) {
            Text(it.title)
        }
    }
}

