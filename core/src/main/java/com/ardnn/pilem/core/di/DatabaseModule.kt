package com.ardnn.pilem.core.di

import android.content.Context
import androidx.room.Room
import com.ardnn.pilem.core.BuildConfig
import com.ardnn.pilem.core.data.source.local.room.PilemDao
import com.ardnn.pilem.core.data.source.local.room.PilemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PilemDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSWORD_DB.toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            PilemDatabase::class.java,
            "Pilem.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun providePilemDao(database: PilemDatabase): PilemDao =
        database.pilemDao()
}