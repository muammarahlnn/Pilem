package com.ardnn.pilem.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(pilemUseCase: PilemUseCase): ViewModel() {

    private var section = 0

    fun setSection(section: Int) {
        this.section = section
    }

    val movies: LiveData<Resource<List<Movie>>> =
        LiveDataReactiveStreams.fromPublisher(pilemUseCase.getMovies(section))
}