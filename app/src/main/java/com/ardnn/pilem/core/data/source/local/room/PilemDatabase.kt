package com.ardnn.pilem.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ardnn.pilem.core.data.source.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false)
abstract class PilemDatabase : RoomDatabase() {

    abstract fun pilemDao(): PilemDao
}