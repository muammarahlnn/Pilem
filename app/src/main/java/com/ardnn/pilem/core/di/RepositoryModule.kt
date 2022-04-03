package com.ardnn.pilem.core.di

import com.ardnn.pilem.core.data.PilemRepositoryImpl
import com.ardnn.pilem.core.domain.repository.PilemRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class,DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(pilemRepository: PilemRepositoryImpl): PilemRepository
}