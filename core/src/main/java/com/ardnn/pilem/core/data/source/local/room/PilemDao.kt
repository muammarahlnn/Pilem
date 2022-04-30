package com.ardnn.pilem.core.data.source.local.room

import androidx.room.*
import com.ardnn.pilem.core.data.source.local.entity.*
import com.ardnn.pilem.core.data.source.local.entity.relation.SectionMovieCrossRef
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PilemDao {

//    @Query(
//        "SELECT * FROM movie " +
//                "JOIN section_movie_cross_ref ON section_id = section_movie_cross_ref.section_id " +
//                "JOIN movie ON section_movie_cross_ref.movie_id = movie.movie_id " +
//                "WHERE section.section_id = :section " +
//                "ORDER BY popularity DESC"
//    )
//    @Transaction
//    @RawQuery(observedEntities = [MovieEntity::class])
//    fun getMovies(query: SupportSQLiteQuery): Flowable<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movie_now_playing ORDER BY popularity DESC")
    fun getNowPlayingMovies(): Flowable<List<MovieNowPlayingEntity>>

    @Transaction
    @Query("SELECT * FROM movie_upcoming ORDER BY popularity DESC")
    fun getUpcomingMovies(): Flowable<List<MovieUpcomingEntity>>

    @Transaction
    @Query("SELECT * FROM movie_popular ORDER BY popularity DESC")
    fun getPopularMovies(): Flowable<List<MoviePopularEntity>>

    @Transaction
    @Query("SELECT * FROM movie_top_rated ORDER BY popularity DESC")
    fun getTopRatedMovies(): Flowable<List<MovieTopRatedEntity>>

    @Query("SELECT * FROM movie_now_playing where is_favorite = 1")
    fun getFavoriteMovies(): Flowable<List<MovieNowPlayingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNowPlayingMovies(movies: List<MovieNowPlayingEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovies(movies: List<MovieUpcomingEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movies: List<MoviePopularEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMovies(movies: List<MovieTopRatedEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSection(sectionEntity: SectionEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSectionMovieCrossRef(crossRef: SectionMovieCrossRef): Completable

    @Update
    fun updateFavoriteMovie(movie: MovieNowPlayingEntity)
}