package com.ardnn.pilem.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ardnn.pilem.core.data.source.local.entity.*
import com.ardnn.pilem.core.data.source.local.entity.relation.SectionMovieCrossRef

@Database(
    entities = [
        MovieNowPlayingEntity::class,
        MovieUpcomingEntity::class,
        MoviePopularEntity::class,
        MovieTopRatedEntity::class,
        SectionEntity::class,
        SectionMovieCrossRef::class,
    ],
    version = 3,
    exportSchema = false)
abstract class PilemDatabase : RoomDatabase() {

    abstract fun pilemDao(): PilemDao
}