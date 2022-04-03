package com.ardnn.pilem.moviedetail

import androidx.lifecycle.ViewModel
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val pilemUseCase: PilemUseCase
) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        pilemUseCase.setFavoriteMovie(movie, newState)
    }
}