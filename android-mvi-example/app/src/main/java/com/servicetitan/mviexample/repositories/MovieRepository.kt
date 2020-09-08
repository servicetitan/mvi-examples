package com.servicetitan.mviexample.repositories
import com.servicetitan.mviexample.entities.Movie
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.timerTask

@Singleton
class MovieRepository @Inject constructor() {
    private val moviesSubject: BehaviorSubject<List<Movie>> = BehaviorSubject.createDefault(emptyList())
    val movies get(): Observable<List<Movie>> = moviesSubject

    fun setMovies(movies: List<Movie>) {
        moviesSubject.onNext(movies)
    }
}