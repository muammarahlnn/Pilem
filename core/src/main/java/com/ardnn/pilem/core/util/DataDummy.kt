package com.ardnn.pilem.core.util

import com.ardnn.pilem.core.data.source.local.entity.MovieEntity
import com.ardnn.pilem.core.data.source.remote.response.MovieResponse
import com.ardnn.pilem.core.domain.model.Movie

object DataDummy {

    fun generateDummyMovies(): List<Movie> {
        val movies = ArrayList<Movie>()
        for (i in 1..20) {
            val movie = Movie(
                0,
                "",
                "",
                0f,
                0f,
                "",
                "",
                "",
                false,
            )
            movies.add(movie)
        }
        return movies
    }

    fun generateDummyMovieEntities(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        for (i in 1..20) {
            val movie = MovieEntity(
                0,
                "",
                "",
                0f,
                0f,
                "",
                "",
                "",
                false,
            )
            movies.add(movie)
        }
        return movies
    }

    fun generateDummyMovieResponses(): List<MovieResponse> {
        val movies = ArrayList<MovieResponse>()
        for (i in 1..20) {
            val movie = MovieResponse(
                0,
                "",
                "",
                0f,
                0f,
                "",
                "",
                "",
            )
            movies.add(movie)
        }
        return movies
    }
}