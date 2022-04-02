package com.ardnn.pilem.core.util

import com.ardnn.pilem.core.data.source.local.entity.MovieEntity
import com.ardnn.pilem.core.data.source.remote.response.MovieResponse
import com.ardnn.pilem.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> =
        input.map {
            MovieEntity(
                id = it.id,
                title = it.title ?: "",
                releaseDate = it.releaseDate ?: "",
                rating = it.rating ?: 0f,
                popularity = it.popularity ?: 0f,
                overview = it.overview ?: "",
                posterUrl = it.posterUrl ?: "",
                wallpaperUrl = it.wallpaperUrl ?: "",
                isFavorite = false
            )
        }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.rating,
                overview = it.overview,
                posterUrl = it.posterUrl,
                wallpaperUrl = it.wallpaperUrl,
                isFavorite = it.isFavorite,
            )
        }
}