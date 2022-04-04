package com.ardnn.pilem.core.domain.usecase

import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.domain.repository.PilemRepository
import io.reactivex.Flowable
import javax.inject.Inject

class PilemInteractor @Inject constructor(
    private val pilemRepository: PilemRepository
) : PilemUseCase{

    override fun getMovies(): Flowable<Resource<List<Movie>>> =
        pilemRepository.getMovies()

    override fun getFavoriteMovies(): Flowable<List<Movie>> =
        pilemRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        pilemRepository.setFavoriteMovie(movie, state)
    }
}