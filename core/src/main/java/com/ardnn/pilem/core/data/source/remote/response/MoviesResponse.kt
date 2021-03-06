package com.ardnn.pilem.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @field:SerializedName("results")
    val movies: List<MovieResponse>? = null
)

data class MovieResponse(
    @field:SerializedName("id")
    val id: Int = -1,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val rating: Float? = null,

    @field:SerializedName("popularity")
    val popularity: Float? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val posterUrl: String? = null,

    @field:SerializedName("backdrop_path")
    val wallpaperUrl: String? = null,
)