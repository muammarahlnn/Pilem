package com.ardnn.pilem.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(pilemUseCase: PilemUseCase): ViewModel() {

    val movies = LiveDataReactiveStreams.fromPublisher(pilemUseCase.getMovies())
}