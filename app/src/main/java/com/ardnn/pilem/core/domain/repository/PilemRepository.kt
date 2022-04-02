package com.ardnn.pilem.core.domain.repository

import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.core.domain.model.Movie
import io.reactivex.Flowable

interface PilemRepository {

    fun getMovies(): Flowable<Resource<List<Movie>>>
}