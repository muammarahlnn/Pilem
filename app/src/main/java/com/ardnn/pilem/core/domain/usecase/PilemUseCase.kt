package com.ardnn.pilem.core.domain.usecase

import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.core.domain.model.Movie
import io.reactivex.Flowable

interface PilemUseCase {

    fun getMovies(): Flowable<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flowable<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}