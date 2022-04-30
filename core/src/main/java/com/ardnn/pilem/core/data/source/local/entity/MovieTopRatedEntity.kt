package com.ardnn.pilem.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie_top_rated")
data class MovieTopRatedEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "rating")
    val rating: Float,

    @ColumnInfo(name = "popularity")
    val popularity: Float,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_url")
    val posterUrl: String,

    @ColumnInfo(name = "wallpaper_url")
    val wallpaperUrl: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
) : Parcelable

