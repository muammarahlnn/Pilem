package com.ardnn.pilem.core.di

import android.content.Context
import com.ardnn.pilem.core.domain.repository.PilemRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface CoreComponent {

    @Component.Factory
    interface factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): PilemRepository
}