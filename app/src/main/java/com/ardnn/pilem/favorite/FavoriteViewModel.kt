package com.ardnn.pilem.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(pilemUseCase: PilemUseCase) : ViewModel() {

    val favoriteMovies: LiveData<List<Movie>> =
        LiveDataReactiveStreams.fromPublisher(pilemUseCase.getFavoriteMovies())
}