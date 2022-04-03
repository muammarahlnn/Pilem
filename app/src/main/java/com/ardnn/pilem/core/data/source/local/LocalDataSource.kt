package com.ardnn.pilem.core.data.source.local

import com.ardnn.pilem.core.data.source.local.entity.MovieEntity
import com.ardnn.pilem.core.data.source.local.room.PilemDao
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val pilemDao: PilemDao) {

    fun getMovies(): Flowable<List<MovieEntity>> =
        pilemDao.getMovies()

    fun getFavoriteMovies(): Flowable<List<MovieEntity>> =
        pilemDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>): Completable =
        pilemDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        pilemDao.updateFavoriteMovie(movie)
    }
}