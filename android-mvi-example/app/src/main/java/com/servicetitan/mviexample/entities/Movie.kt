package com.servicetitan.mviexample.entities

data class Movie(
    val title: String,
    val year: Int,
    val imdbId: String,
    var type: String,
    var posterUrl: String
)


