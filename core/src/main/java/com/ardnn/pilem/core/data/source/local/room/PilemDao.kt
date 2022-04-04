package com.ardnn.pilem.core.data.source.local.room

import androidx.room.*
import com.ardnn.pilem.core.data.source.local.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PilemDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM movie where is_favorite = 1")
    fun getFavoriteMovies(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>): Completable

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}