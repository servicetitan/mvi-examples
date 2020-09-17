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
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import com.servicetitan.mviexample.state.MovieDetailState
import com.servicetitan.mviexample.ui.fragment.MovieDetailDelegate
import com.servicetitan.mviexample.ui.theme.MVIExampleTheme
import com.servicetitan.mviexample.ui.theme.typography

@Composable
fun MovieDetailView(state: State<MovieDetailState> = mutableStateOf(MovieDetailState.None), delegate: MovieDetailDelegate?) {
    MVIExampleTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                when (state.value) {
                    is MovieDetailState.Loading -> Loading()
                    is MovieDetailState.Received -> {
                        val movieDetail = (state.value as MovieDetailState.Received).movieDetail
                        HeaderContent(movieDetail)
                        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
                        BodyContent(movieDetail)
                    }
                    is MovieDetailState.Error -> Error((state.value as MovieDetailState.Error).error, delegate)
                    else -> {}
                }
            }
        }
    }
}

@Composable
private fun HeaderContent(movieDetail: MovieDetail) {
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
private fun BodyContent(movieDetail: MovieDetail) {
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
private fun RatingCard(rating: Rating) {
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
private fun GenreCard(name: String) {
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
private fun divider() {
    Divider(modifier = Modifier.padding(4.dp).drawOpacity(0.5f), color = Color.LightGray)
}

@Composable
private fun header(header: String) {
    Text(
        text = header,
        style = typography.subtitle2.merge(TextStyle(color = Color.LightGray))
    )
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Error(error: String, delegate: MovieDetailDelegate?) {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        Column {
            Text(text = error, style = typography.h5)
            Button(
                onClick = { delegate?.requestMovieDetails() },
                content = { Text(text = "Reload") }
            )
        }
    }
}

@Composable
private fun spacer() {
    Spacer(modifier = Modifier.preferredHeight(4.dp))
}