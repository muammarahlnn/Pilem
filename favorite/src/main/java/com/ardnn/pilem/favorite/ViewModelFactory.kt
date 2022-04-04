package com.ardnn.pilem.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val pilemUseCase: PilemUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(pilemUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class ${modelClass.name}")
        }
    }
}