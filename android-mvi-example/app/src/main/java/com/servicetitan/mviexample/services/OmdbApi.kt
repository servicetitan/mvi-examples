package com.servicetitan.mviexample.services

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.servicetitan.mviexample.core.Config
import com.servicetitan.mviexample.entities.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.StringReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OmdbApi @Inject constructor(
    private val config: Config
) {
    suspend fun search(title: String): List<Movie> {
        try {
            val json = config.omdbApiUrl.httpGet(listOf("s" to title, "apikey" to config.omdbApiKey)).awaitStringResponse().third
            val rawResult = Klaxon().parseJsonObject(StringReader(json))
            val rawMovies = if(rawResult?.map?.containsKey("Search")) rawResult["Search"] as JsonArray<JsonObject> else null
            if (rawMovies == null) {
                return emptyList()
            }
            val movies = rawMovies.map { Movie(
                it["Title"].toString(),
                it["Year"].toString().toInt(),
                it["imdbID"].toString(),
                it["Type"].toString(),
                it["Poster"].toString(),
            ) }
            return movies
        } catch (ex: Exception) {
            return emptyList()
        }
    }
}