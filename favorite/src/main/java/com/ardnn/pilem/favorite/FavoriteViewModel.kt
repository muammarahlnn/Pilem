package com.ardnn.pilem.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.domain.usecase.PilemUseCase

class FavoriteViewModel (pilemUseCase: PilemUseCase) : ViewModel() {

    val favoriteMovies: LiveData<List<Movie>> =
        LiveDataReactiveStreams.fromPublisher(pilemUseCase.getFavoriteMovies())
}