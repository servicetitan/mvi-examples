package com.servicetitan.mviexample.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import com.servicetitan.mviexample.events.MovieEvent
import com.servicetitan.mviexample.processors.MovieEventProcessor
import com.servicetitan.mviexample.state.MovieState
import com.servicetitan.mviexample.ui.fragment.MoviesFragmentDirections
import com.servicetitan.mviexample.ui.theme.MVIExampleTheme
import timber.log.Timber

data class Payload(val searchQuery: MutableState<String> = mutableStateOf(""))

class MoviesView(private val eventProcessor: MovieEventProcessor) : BaseView<MovieEvent, MovieState>(eventProcessor) {

    private var payload = Payload()

    @Composable
    fun MovieSearch(navController: NavController) {
        MVIExampleTheme {
            Surface(color = MaterialTheme.colors.background) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        TextField(
                            value = payload.searchQuery.value,
                            onValueChange = { payload.searchQuery.value = it },
                            label = { Text("Search for movies") }
                        )
                        Spacer(Modifier.preferredWidth(12.dp))
                        Button(
                            modifier = Modifier.gravity(Alignment.CenterVertically),
                            onClick = { eventProcessor.eventDispatcher.onNext(MovieEvent.Request(payload))},
                            content = { Text(text = "Search") }
                        )
                    }
                    when (viewState.value) {
                        is MovieState.Loading -> Loading()
                        is MovieState.Received -> Movie(navController)
//                        is MovieState.NavigateMovieDetail ->
//                            navController.navigate(MoviesFragmentDirections
//                                .toMovieDetails((viewState.value as MovieState.NavigateMovieDetail).movieId))
                        else -> Timber.d("No State")
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
    private fun Movie(navController: NavController) {
        LazyColumnFor(items = (viewState.value as MovieState.Received).movies) {
            Card(
                shape = MaterialTheme.shapes.medium, contentColor = Color.Black,
                modifier = Modifier.padding(12.dp).clickable(onClick = {
//                    eventProcessor.eventDispatcher.onNext(MovieEvent.MovieDetails(it.imdbId))
                    navController.navigate(MoviesFragmentDirections
                               .toMovieDetails(it.imdbId))
                })
            ) {
                Column(modifier = Modifier.background(Color.White)) {
                    Image(
                        generateImage(url = it.posterUrl).value,
                        modifier = Modifier.preferredHeight(180.dp).fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth
                    )

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
}



