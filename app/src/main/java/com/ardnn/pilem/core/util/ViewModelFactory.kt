package com.ardnn.pilem.core.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ardnn.pilem.core.di.Injection
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import com.ardnn.pilem.home.HomeViewModel

class ViewModelFactory private constructor(
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

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.providePilemUseCase(context))
            }
    }
}