package com.ardnn.pilem.core.util

import com.ardnn.pilem.core.data.source.local.entity.MovieNowPlayingEntity
import com.ardnn.pilem.core.data.source.local.entity.MoviePopularEntity
import com.ardnn.pilem.core.data.source.local.entity.MovieTopRatedEntity
import com.ardnn.pilem.core.data.source.local.entity.MovieUpcomingEntity
import com.ardnn.pilem.core.data.source.remote.response.MovieResponse
import com.ardnn.pilem.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToNowPlayingEntities(input: List<MovieResponse>): List<MovieNowPlayingEntity> =
        input.map {
            MovieNowPlayingEntity(
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

    fun mapResponsesToUpcomingEntities(input: List<MovieResponse>): List<MovieUpcomingEntity> =
        input.map {
            MovieUpcomingEntity(
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

    fun mapResponsesToPopularEntities(input: List<MovieResponse>): List<MoviePopularEntity> =
        input.map {
            MoviePopularEntity(
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

    fun mapResponsesToTopRatedEntities(input: List<MovieResponse>): List<MovieTopRatedEntity> =
        input.map {
            MovieTopRatedEntity(
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

    fun mapNowPlayingEntitiesToDomain(input: List<MovieNowPlayingEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.rating,
                popularity = it.popularity,
                overview = it.overview,
                posterUrl = it.posterUrl,
                wallpaperUrl = it.wallpaperUrl,
                isFavorite = it.isFavorite,
            )
        }

    fun mapUpcomingEntitiesToDomain(input: List<MovieUpcomingEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.rating,
                popularity = it.popularity,
                overview = it.overview,
                posterUrl = it.posterUrl,
                wallpaperUrl = it.wallpaperUrl,
                isFavorite = it.isFavorite,
            )
        }

    fun mapPopularEntitiesToDomain(input: List<MoviePopularEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.rating,
                popularity = it.popularity,
                overview = it.overview,
                posterUrl = it.posterUrl,
                wallpaperUrl = it.wallpaperUrl,
                isFavorite = it.isFavorite,
            )
        }

    fun mapTopRatedEntitiesToDomain(input: List<MovieTopRatedEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.rating,
                popularity = it.popularity,
                overview = it.overview,
                posterUrl = it.posterUrl,
                wallpaperUrl = it.wallpaperUrl,
                isFavorite = it.isFavorite,
            )
        }

    fun mapDomainToEntity(input: Movie): MovieNowPlayingEntity =
        MovieNowPlayingEntity(
            id = input.id,
            title = input.title,
            releaseDate = input.releaseDate,
            rating = input.rating,
            popularity = input.popularity,
            overview = input.overview,
            posterUrl = input.posterUrl,
            wallpaperUrl = input.wallpaperUrl,
            isFavorite = input.isFavorite,
        )
}