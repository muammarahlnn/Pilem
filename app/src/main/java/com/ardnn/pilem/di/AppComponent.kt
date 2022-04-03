package com.ardnn.pilem.di

import com.ardnn.pilem.core.di.CoreComponent
import com.ardnn.pilem.home.HomeFragment
import com.ardnn.pilem.moviedetail.MovieDetailFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)

    fun inject(fragment: MovieDetailFragment)
}