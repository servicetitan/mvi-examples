package com.servicetitan.mviexample.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.ui.tooling.preview.Preview
import com.servicetitan.mviexample.entities.Movie
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.state.BaseState
import com.servicetitan.mviexample.state.MovieState
import com.servicetitan.mviexample.ui.fragment.MoviesFragmentDirections
import com.servicetitan.mviexample.ui.theme.MVIExampleTheme
import timber.log.Timber

sealed class MovieSearchProps {
    object Loading: MovieSearchProps()
    class Received(
        val movies: List<Movie> = emptyList(),
        val onSearch: (searchQuery: String) -> Unit = { },
        val onMovieClick: (imdbId: String) -> Unit = { }
    ): MovieSearchProps()
}

@Composable
@Preview
fun MovieSearch(props: MovieSearchProps = MovieSearchProps.Received()) {
    val (searchQuery, setSearchQuery) = remember { mutableStateOf("") }

    MVIExampleTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(8.dp)
                ) {
                    TextField(
                        value = searchQuery,
                        onValueChange = setSearchQuery,

                        label = { Text("Search for movies") }
                    )
                    Spacer(Modifier.preferredWidth(12.dp))
                    Button(
                        modifier = Modifier.gravity(Alignment.CenterVertically),
                        onClick = { if (props is MovieSearchProps.Received) { props.onSearch(searchQuery) } },
                        enabled = props is MovieSearchProps.Received,
                        content = { Text(text = "Search") }
                    )
                }
                when (props) {
                    is MovieSearchProps.Loading -> Loading()
                    is MovieSearchProps.Received -> Movies(props.movies, props.onMovieClick)
                }
            }
        }
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Movies(movies: List<Movie>, onMovieClick: (imdbId: String) -> Unit) {
    LazyColumnFor(items = movies) {
        Card(
            shape = MaterialTheme.shapes.medium, contentColor = Color.Black,
            modifier = Modifier.padding(12.dp).clickable(onClick = { onMovieClick(it.imdbId) })
        ) {
            Column(modifier = Modifier.background(Color.White)) {
                /*
                Image(
                    generateImage(url = it.posterUrl).value,
                    modifier = Modifier.preferredHeight(180.dp).fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillWidth
                )
                */

                Spacer(Modifier.preferredHeight(8.dp))

                Text(
                    modifier = Modifier.padding(start = 8.dp).gravity(Alignment.CenterHorizontally),
                    text = it.title, style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                )

                Spacer(Modifier.preferredHeight(8.dp))
            }
        }
    }
    Spacer(Modifier.preferredHeight(4.dp))
}
