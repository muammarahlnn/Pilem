package com.ardnn.pilem.core.domain.usecase

import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.domain.repository.PilemRepository
import io.reactivex.Flowable

class PilemInteractor(
    private val pilemRepository: PilemRepository
) : PilemUseCase{

    override fun getMovies(): Flowable<Resource<List<Movie>>> =
        pilemRepository.getMovies()
}