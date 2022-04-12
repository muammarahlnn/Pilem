package com.ardnn.pilem.core.data

import com.ardnn.pilem.core.data.source.local.LocalDataSource
import com.ardnn.pilem.core.data.source.remote.RemoteDataSource
import com.ardnn.pilem.core.util.AppExecutors
import com.ardnn.pilem.core.util.DataDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class PilemRepositoryImplTest {

    private val remote = mockk<RemoteDataSource>()

    private val local = mockk<LocalDataSource>()

    private val appExecutors = mockk<AppExecutors>()

    private val repository = FakePilemRepositoryImpl(remote, local, appExecutors)

    private val dummyMovieEntities = DataDummy.generateDummyMovieEntities()

    private val dummyMovieResponses = DataDummy.generateDummyMovieResponses()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun getMovies() {
        // given
        every { local.getMovies() } returns Flowable.just(dummyMovieEntities)

        // when
        repository.getMovies()
            .test()
            .dispose()

        // then
        verify { local.getMovies() }

        val movies = Resource.Success(dummyMovieEntities)
        assertNotNull(movies.data)
        assertEquals(dummyMovieResponses.size.toLong(), movies.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovies() {
        // given
        every { local.getFavoriteMovies() } returns Flowable.just(dummyMovieEntities)

        // when
        repository.getFavoriteMovies()
            .test()
            .dispose()

        // then
        verify { local .getFavoriteMovies()}

        val favoriteMovies = Resource.Success(dummyMovieEntities)
        assertNotNull(favoriteMovies.data)
        assertEquals(dummyMovieResponses.size.toLong(), favoriteMovies.data?.size?.toLong())
    }
}