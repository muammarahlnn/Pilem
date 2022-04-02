package com.ardnn.pilem.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val rating: Float,
    val overview: String,
    val posterUrl: String,
    val wallpaperUrl: String,
    var isFavorite: Boolean
) : Parcelable