package com.ardnn.pilem.di

import com.ardnn.pilem.core.domain.usecase.PilemInteractor
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun providePilemUseCase(pilemInteractor: PilemInteractor): PilemUseCase
}