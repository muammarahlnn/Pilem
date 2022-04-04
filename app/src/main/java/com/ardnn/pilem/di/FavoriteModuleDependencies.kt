package com.ardnn.pilem.di

import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun pilemUseCase(): PilemUseCase
}