package com.ardnn.pilem.core.domain.repository

import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.core.domain.model.Movie
import io.reactivex.Flowable

interface PilemRepository {

    fun getMovies(section: Int): Flowable<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flowable<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}