package com.ardnn.pilem.core.data.source.local

import com.ardnn.pilem.core.data.source.local.entity.*
import com.ardnn.pilem.core.data.source.local.entity.relation.SectionMovieCrossRef
import com.ardnn.pilem.core.data.source.local.room.PilemDao
import io.reactivex.Completable
import io.reactivex.Flowable
import java.lang.StringBuilder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val pilemDao: PilemDao) {

    fun getNowPlayingMovies(): Flowable<List<MovieNowPlayingEntity>> =
        pilemDao.getNowPlayingMovies()

    fun getUpcomingMovies(): Flowable<List<MovieUpcomingEntity>> =
        pilemDao.getUpcomingMovies()

    fun getPopularMovies(): Flowable<List<MoviePopularEntity>> =
        pilemDao.getPopularMovies()

    fun getTopRatedMovies(): Flowable<List<MovieTopRatedEntity>> =
        pilemDao.getTopRatedMovies()

    fun getFavoriteMovies(): Flowable<List<MovieNowPlayingEntity>> =
        pilemDao.getFavoriteMovies()

    fun insertNowPlayingMovies(movies: List<MovieNowPlayingEntity>): Completable =
        pilemDao.insertNowPlayingMovies(movies)

    fun insertUpcomingMovies(movies: List<MovieUpcomingEntity>): Completable =
        pilemDao.insertUpcomingMovies(movies)

    fun insertPopularMovies(movies: List<MoviePopularEntity>): Completable =
        pilemDao.insertPopularMovies(movies)

    fun insertTopRatedMovies(movies: List<MovieTopRatedEntity>): Completable =
        pilemDao.insertTopRatedMovies(movies)

    fun insertSection(sectionEntity: SectionEntity): Completable =
        pilemDao.insertSection(sectionEntity)

    fun insertSectionMovieCrossRef(crossRef: SectionMovieCrossRef): Completable =
        pilemDao.insertSectionMovieCrossRef(crossRef)

    fun setFavoriteMovie(movie: MovieNowPlayingEntity, newState: Boolean) {
        movie.isFavorite = newState
        pilemDao.updateFavoriteMovie(movie)
    }
}