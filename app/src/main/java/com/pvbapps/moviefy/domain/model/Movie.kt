package com.pvbapps.moviefy.domain.model

import com.google.gson.annotations.SerializedName
import com.pvbapps.moviefy.domain.offline.MovieOfflineEntity

class Movie(
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    val id: Int,
    val video: Boolean,
    @SerializedName("media_type")
    val mediaType: String,
    val title: String,
    val popularity: Float,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val adult: Boolean,
    val overview: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("release_date")
    val releaseDate: String
) {
    fun toDatabaseEntity(): MovieOfflineEntity {
        var movieEntity = MovieOfflineEntity()
        movieEntity.voteAverage = voteAverage
        movieEntity.voteCount = voteCount
        movieEntity.id = id
        movieEntity.video = video
        movieEntity.mediaType = mediaType
        movieEntity.title = title
        movieEntity.popularity = popularity
        movieEntity.posterPath = posterPath
        movieEntity.originalLanguage = originalLanguage
        movieEntity.originalTitle = originalTitle
        movieEntity.adult = adult
        movieEntity.overview = overview
        movieEntity.backdropPath = backdropPath
        movieEntity.releaseDate = releaseDate
        return movieEntity
    }
}