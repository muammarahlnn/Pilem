package com.ardnn.pilem.core.data.source.local

import com.ardnn.pilem.core.data.source.local.entity.MovieEntity
import com.ardnn.pilem.core.data.source.local.room.PilemDao
import io.reactivex.Completable
import io.reactivex.Flowable

class LocalDataSource private constructor(private val pilemDao: PilemDao) {

    fun getMovies(): Flowable<List<MovieEntity>> =
        pilemDao.getMovies()

    fun insertMovies(movies: List<MovieEntity>): Completable =
        pilemDao.insertMovies(movies)

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(pilemDao: PilemDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(pilemDao)
            }
    }
}