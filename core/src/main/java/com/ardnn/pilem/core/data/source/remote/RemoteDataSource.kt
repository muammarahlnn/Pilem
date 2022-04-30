package com.ardnn.pilem.core.data.source.remote

import com.ardnn.pilem.core.BuildConfig
import com.ardnn.pilem.core.data.source.remote.network.ApiResponse
import com.ardnn.pilem.core.data.source.remote.network.ApiService
import com.ardnn.pilem.core.data.source.remote.response.MovieResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getNowPlayingMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieResponse>>>()
        val client = apiService.getNowPlayingMovies(BuildConfig.API_KEY)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.movies
                dataArray?.let {
                    resultData.onNext(
                        if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray)
                        else ApiResponse.Empty
                    )
                }
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Timber.e(error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getUpcomingMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieResponse>>>()
        val client = apiService.getUpcomingMovies(BuildConfig.API_KEY)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.movies
                dataArray?.let {
                    resultData.onNext(
                        if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray)
                        else ApiResponse.Empty
                    )
                }
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Timber.e(error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getPopularMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieResponse>>>()
        val client = apiService.getPopularMovies(BuildConfig.API_KEY)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.movies
                dataArray?.let {
                    resultData.onNext(
                        if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray)
                        else ApiResponse.Empty
                    )
                }
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Timber.e(error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getTopRatedMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieResponse>>>()
        val client = apiService.getTopRatedMovies(BuildConfig.API_KEY)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.movies
                dataArray?.let {
                    resultData.onNext(
                        if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray)
                        else ApiResponse.Empty
                    )
                }
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Timber.e(error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}