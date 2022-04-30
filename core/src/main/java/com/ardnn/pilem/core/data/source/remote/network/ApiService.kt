package com.ardnn.pilem.core.data.source.remote.network

import com.ardnn.pilem.core.data.source.remote.response.MoviesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
    ): Flowable<MoviesResponse>

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
    ): Flowable<MoviesResponse>

    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
    ): Flowable<MoviesResponse>

    @GET("3/movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
    ): Flowable<MoviesResponse>
}