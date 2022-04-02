package com.ardnn.pilem.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ardnn.pilem.core.domain.usecase.PilemUseCase

class HomeViewModel(pilemUseCase: PilemUseCase): ViewModel() {

    val movies = LiveDataReactiveStreams.fromPublisher(pilemUseCase.getMovies())
}