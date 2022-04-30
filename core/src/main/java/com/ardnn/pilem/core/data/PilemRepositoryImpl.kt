package com.ardnn.pilem.core.data

import com.ardnn.pilem.core.data.source.local.LocalDataSource
import com.ardnn.pilem.core.data.source.local.entity.SectionEntity
import com.ardnn.pilem.core.data.source.local.entity.relation.SectionMovieCrossRef
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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PilemRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : PilemRepository {

    override fun getMovies(section: Int): Flowable<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDb(): Flowable<List<Movie>> =
                when (section) {
                    0 -> {
                        localDataSource.getNowPlayingMovies().map {
                            DataMapper.mapNowPlayingEntitiesToDomain(it)
                        }
                    }
                    1 -> {
                        localDataSource.getUpcomingMovies().map {
                            DataMapper.mapUpcomingEntitiesToDomain(it)
                        }
                    }
                    2 -> {
                        localDataSource.getPopularMovies().map {
                            DataMapper.mapPopularEntitiesToDomain(it)
                        }
                    }
                    3 -> {
                        localDataSource.getTopRatedMovies().map {
                            DataMapper.mapTopRatedEntitiesToDomain(it)
                        }
                    }
                    else -> {
                        localDataSource.getNowPlayingMovies().map {
                            DataMapper.mapNowPlayingEntitiesToDomain(it)
                        }
                    }
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> =
                when (section) {
                    0 -> remoteDataSource.getNowPlayingMovies()
                    1 -> remoteDataSource.getUpcomingMovies()
                    2 -> remoteDataSource.getPopularMovies()
                    3 -> remoteDataSource.getTopRatedMovies()
                    else -> remoteDataSource.getNowPlayingMovies() // default
                }

            override fun saveCallResult(data: List<MovieResponse>) {
                // insert movies
                when (section) {
                    0 -> {
                        val movieEntities = DataMapper.mapResponsesToNowPlayingEntities(data)
                        localDataSource.insertNowPlayingMovies(movieEntities)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                    }
                    1 -> {
                        val movieEntities = DataMapper.mapResponsesToUpcomingEntities(data)
                        localDataSource.insertUpcomingMovies(movieEntities)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                    }
                    2 -> {
                        val movieEntities = DataMapper.mapResponsesToPopularEntities(data)
                        localDataSource.insertPopularMovies(movieEntities)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                    }
                    3 -> {
                        val movieEntities = DataMapper.mapResponsesToTopRatedEntities(data)
                        localDataSource.insertTopRatedMovies(movieEntities)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                    }
                }

//                // insert section
//                val sectionEntity = SectionEntity(section)
//                localDataSource.insertSection(sectionEntity)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe()
//
//                // insert section movie cross ref
//                for (movie in movieEntities) {
//                    val crossRef = SectionMovieCrossRef(section, movie.id)
//                    localDataSource.insertSectionMovieCrossRef(crossRef)
//                }
            }
        }.asFlowable()
    }

    override fun getFavoriteMovies(): Flowable<List<Movie>> =
        localDataSource.getFavoriteMovies().map {
            DataMapper.mapNowPlayingEntitiesToDomain(it)
        }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIo().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }
}