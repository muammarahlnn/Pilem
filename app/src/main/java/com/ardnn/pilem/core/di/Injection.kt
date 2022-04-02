package com.ardnn.pilem.core.di

import android.content.Context
import com.ardnn.pilem.core.data.PilemRepositoryImpl
import com.ardnn.pilem.core.data.source.local.LocalDataSource
import com.ardnn.pilem.core.data.source.local.room.PilemDatabase
import com.ardnn.pilem.core.data.source.remote.RemoteDataSource
import com.ardnn.pilem.core.data.source.remote.network.ApiConfig
import com.ardnn.pilem.core.domain.usecase.PilemInteractor
import com.ardnn.pilem.core.domain.usecase.PilemUseCase
import com.ardnn.pilem.core.util.AppExecutors

object Injection {

    private fun provideRepository(context: Context): PilemRepositoryImpl {
        val database = PilemDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.pilemDao())
        val appExecutors = AppExecutors()

        return PilemRepositoryImpl.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun providePilemUseCase(context: Context): PilemUseCase {
        val repository = provideRepository(context)
        return PilemInteractor(repository)
    }
}