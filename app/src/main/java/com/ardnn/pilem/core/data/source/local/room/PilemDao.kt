package com.ardnn.pilem.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ardnn.pilem.core.data.source.local.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PilemDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>): Completable
}