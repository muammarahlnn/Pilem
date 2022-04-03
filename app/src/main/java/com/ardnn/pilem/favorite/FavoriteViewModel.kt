package com.ardnn.pilem.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(pilemUseCase: PilemUseCase) : ViewModel() {

    val favoriteMovies = LiveDataReactiveStreams.fromPublisher(pilemUseCase.getFavoriteMovies())
}