package com.ardnn.pilem.core.data

import com.ardnn.pilem.core.data.source.local.LocalDataSource
import com.ardnn.pilem.core.data.source.remote.RemoteDataSource
import com.ardnn.pilem.core.data.source.remote.network.ApiResponse
import com.ardnn.pilem.core.data.source.remote.response.MovieResponse
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.domain.repository.PilemRepository
import com.ardnn.pilem.core.util.AppExecutors
import com.ardnn.pilem.core.util.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PilemRepositoryImpl private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : PilemRepository {

    override fun getMovies(): Flowable<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDb(): Flowable<List<Movie>> {
                return localDataSource.getMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieEntities = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieEntities)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    companion object {
        @Volatile
        private var instance: PilemRepositoryImpl? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
        ) : PilemRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: PilemRepositoryImpl(
                    remoteDataSource,
                    localDataSource,
                    appExecutors
                )
            }
    }
}