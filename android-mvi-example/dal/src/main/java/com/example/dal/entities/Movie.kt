package com.example.dal.entities

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SearchMovie(
    @SerializedName("Search") val search: List<Movie> = emptyList()
)

@Entity
data class Movie(
    @SerializedName("Title") var title: String = "",
    @SerializedName("Year") var year: String = "",
    @PrimaryKey @SerializedName("imdbID") var imdbId: String = "",
    @SerializedName("Type") var type: String = "",
    @SerializedName("Poster") var posterUrl: String = "",
    @Ignore var favoriteRating: MutableState<Int> = mutableStateOf(0)
)


