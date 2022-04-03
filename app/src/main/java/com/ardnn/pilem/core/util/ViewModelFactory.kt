package com.ardnn.pilem.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import com.ardnn.pilem.di.AppScope
import com.ardnn.pilem.home.HomeViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(
    private val pilemUseCase: PilemUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(pilemUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
}