package com.servicetitan.mviexample.ui.view

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.example.dal.entities.Movie
import com.servicetitan.mviexample.state.MovieState
import com.servicetitan.mviexample.ui.theme.MVIExampleTheme

@Composable
@Preview
fun MovieSearch(
    state: State<MovieState> = mutableStateOf(MovieState.None),
    searchWithQuery: (String) -> Unit = {},
    movieSelected: (String) -> Unit = {}
) {
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }

    MVIExampleTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(8.dp)
                ) {
                    TextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        label = { Text("Search for movies") }
                    )
                    Spacer(Modifier.preferredWidth(12.dp))
                    Button(
                        modifier = Modifier.gravity(Alignment.CenterVertically),
                        onClick = { searchWithQuery(searchQuery.value.text)  },
                        content = { Text(text = "Search") }
                    )
                }

                when(state.value) {
                    is MovieState.Loading -> Loading()
                    is MovieState.Received ->
                        Movies(movies = (state.value as MovieState.Received).movies, movieSelected)
                    else -> {}
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
private fun Movies(movies: List<Movie>, movieSelected: (String) -> Unit) {
    LazyColumnFor(items = movies) {
        Card(
            shape = MaterialTheme.shapes.medium, contentColor = Color.Black,
            modifier = Modifier.padding(12.dp).clickable(onClick = { movieSelected(it.imdbId) })
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
