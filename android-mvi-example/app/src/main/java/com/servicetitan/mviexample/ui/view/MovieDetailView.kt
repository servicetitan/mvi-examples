package com.servicetitan.mviexample.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.servicetitan.mviexample.entities.MovieDetail
import com.servicetitan.mviexample.entities.Rating
import com.servicetitan.mviexample.events.MovieDetailEvent
import com.servicetitan.mviexample.processors.MovieDetailEventProcessor
import com.servicetitan.mviexample.state.MovieDetailState
import com.servicetitan.mviexample.ui.theme.MVIExampleTheme
import com.servicetitan.mviexample.ui.theme.typography

class MovieDetailView(private val eventProcessor: MovieDetailEventProcessor) :
    BaseView<MovieDetailEvent, MovieDetailState>(eventProcessor) {

    @Composable
    fun MovieDetailView(movieId: String) {
        eventProcessor.eventDispatcher.onNext(MovieDetailEvent.Request(movieId))
        MVIExampleTheme {
            Surface(color = MaterialTheme.colors.background) {
                Column {
                    when (viewState.value) {
                        is MovieDetailState.Loading -> Loading()
                        is MovieDetailState.Received -> {
                            val movieDetailState = viewState.value as MovieDetailState.Received
                            HeaderContent(movieDetailState.movieDetail)
                            Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
                            BodyContent(movieDetailState.movieDetail)
                        }
                        is MovieDetailState.Error -> Error((viewState.value as MovieDetailState.Error).error)
                    }
                }
            }
        }
    }

    @Composable
    fun HeaderContent(movieDetail: MovieDetail) {
        Row(modifier = Modifier.fillMaxWidth().preferredHeight(200.dp)) {
            Image(
                generateImage(url = movieDetail.poster).value,
                modifier = Modifier.preferredHeight(200.dp).preferredWidth(180.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillWidth
            )

            Column(modifier = Modifier.weight(0.6f).padding(horizontal = 8.dp, vertical = 4.dp)) {
                Text(
                    text = movieDetail.title,
                    style = typography.h5
                        .merge(TextStyle(fontWeight = FontWeight(FontWeight.Bold.weight)))
                )
                Text(text = movieDetail.released)

                Divider(
                    modifier = Modifier.padding(4.dp).drawOpacity(0.5f),
                    color = Color.LightGray
                )
                Text(
                    text = movieDetail.director,
                    style = typography.subtitle1.merge(
                        TextStyle(fontWeight = FontWeight(FontWeight.Bold.weight))
                    )
                )
                Text(text = "Writer: ${movieDetail.writer}")
                Text(text = "${movieDetail.runtime}utes runtime in ${movieDetail.language}")
            }
        }
    }

    @Composable
    fun BodyContent(movieDetail: MovieDetail) {
        Column(modifier = Modifier.padding(4.dp)) {
            divider()
            header("Ratings")
            spacer()
            LazyRowFor(items = movieDetail.ratings) {
                RatingCard(rating = it)
            }
            spacer()

            divider()
            header("Overview")
            Text(text = movieDetail.plot, style = typography.body1)
            spacer()

            divider()
            header("Genre")
            LazyRowFor(items = movieDetail.genre.split(",").map { it.trim() }) {
                GenreCard(it)
            }
            spacer()
            divider()
        }
    }

    @Composable
    fun RatingCard(rating: Rating) {
        Card(border = BorderStroke(1.dp, Color.LightGray)) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = rating.value,
                    modifier = Modifier.gravity(Alignment.CenterHorizontally).padding(top = 4.dp)
                )
                Spacer(modifier = Modifier.preferredHeight(4.dp))
                Text(
                    text = rating.source,
                    modifier = Modifier.gravity(Alignment.CenterHorizontally).padding(top = 4.dp)
                )
            }
        }
        Divider(modifier = Modifier.padding(4.dp))
    }

    @Composable
    fun GenreCard(name: String) {
        Card(border = BorderStroke(1.dp, Color.LightGray), shape = CircleShape) {
            Text(
                text = name,
                modifier = Modifier.gravity(Alignment.CenterHorizontally).padding(12.dp),
                style = typography.subtitle1
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }

    @Composable
    fun divider() {
        Divider(modifier = Modifier.padding(4.dp).drawOpacity(0.5f), color = Color.LightGray)
    }

    @Composable
    fun header(header: String) {
        Text(
            text = header,
            style = typography.subtitle2.merge(TextStyle(color = Color.LightGray))
        )
    }

    @Composable
    fun Loading() {
        Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun Error(error: String) {
        Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
            Text(text = error, style = typography.h5)
        }
    }

    @Composable
    fun spacer() {
        Spacer(modifier = Modifier.preferredHeight(4.dp))
    }
}