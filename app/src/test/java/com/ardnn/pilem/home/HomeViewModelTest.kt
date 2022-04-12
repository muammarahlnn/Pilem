package com.ardnn.pilem.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.domain.usecase.PilemInteractor
import com.ardnn.pilem.core.util.DataDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    private val pilemInteractor = mockk<PilemInteractor>(relaxed = true)

    private val observer = mockk<Observer<Resource<List<Movie>>>>(relaxed = true)

    private val dummyMovies = DataDummy.generateDummyMovies()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        viewModel = HomeViewModel(pilemInteractor)
    }

    @Test
    fun getMovies() {
        // given
        every { pilemInteractor.getMovies() } returns
                Flowable.just(Resource.Success(dummyMovies))

        // when
        pilemInteractor.getMovies()
            .test()
            .dispose()

        viewModel.movies.observeForever(observer)

        // then
        verify { pilemInteractor.getMovies() }
        assertNotNull(viewModel.movies)
    }
}