package com.ardnn.pilem.core.domain.usecase

import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.core.util.DataDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test


class PilemInteractorTest {

    private val pilemInteractor = mockk<PilemInteractor>()

    private val dummyMovies = DataDummy.generateDummyMovies()

    @Test
    fun getMovies() {
        // given
        every { pilemInteractor.getMovies() } returns
                Flowable.just(Resource.Success(dummyMovies))

        // when
        pilemInteractor.getMovies()
            .test()
            .dispose()

        // then
        verify { pilemInteractor.getMovies() }
        assertNotNull(pilemInteractor.getMovies())
    }

    @Test
    fun getFavoriteMovies() {
        // given
        val expected = Flowable.just(dummyMovies)
        every { pilemInteractor.getFavoriteMovies() } returns expected

        // when
        pilemInteractor.getFavoriteMovies()
            .test()
            .dispose()

        // then
        verify { pilemInteractor.getFavoriteMovies() }
        assertEquals(pilemInteractor.getFavoriteMovies(), expected)
    }
}