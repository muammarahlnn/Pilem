package com.ardnn.pilem.core.data.source.remote

import android.util.Log
import com.ardnn.pilem.BuildConfig
import com.ardnn.pilem.core.data.source.remote.network.ApiResponse
import com.ardnn.pilem.core.data.source.remote.network.ApiService
import com.ardnn.pilem.core.data.source.remote.response.MovieResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieResponse>>>()
        val client = apiService.getMovies(BuildConfig.API_KEY)
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
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}