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
    val title: String,
    val popularity: Float,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val adult: Boolean,
    val overview: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    var isTopRated: Boolean = false,
    var isPopular: Boolean = false,
    var isUpcoming: Boolean = false
) {
    companion object {
        fun getMovieFromDatabaseEntity(movieOfflineEntity: MovieOfflineEntity): Movie {
            return Movie(
                movieOfflineEntity.voteAverage,
                movieOfflineEntity.voteCount,
                movieOfflineEntity.id,
                movieOfflineEntity.video,
                movieOfflineEntity.title,
                movieOfflineEntity.popularity,
                movieOfflineEntity.posterPath,
                movieOfflineEntity.originalLanguage,
                movieOfflineEntity.originalTitle,
                movieOfflineEntity.adult,
                movieOfflineEntity.overview,
                movieOfflineEntity.backdropPath,
                movieOfflineEntity.releaseDate,
                movieOfflineEntity.isTopRated,
                movieOfflineEntity.isPopular,
                movieOfflineEntity.isUpcoming
            )
        }
    }

    fun toDatabaseEntity(): MovieOfflineEntity {
        var movieEntity = MovieOfflineEntity()
        movieEntity.voteAverage = voteAverage
        movieEntity.voteCount = voteCount
        movieEntity.id = id
        movieEntity.video = video
        movieEntity.title = title
        movieEntity.popularity = popularity
        movieEntity.posterPath = posterPath
        movieEntity.originalLanguage = originalLanguage
        movieEntity.originalTitle = originalTitle
        movieEntity.adult = adult
        movieEntity.overview = overview
        movieEntity.backdropPath = backdropPath
        movieEntity.releaseDate = releaseDate
        movieEntity.isTopRated = isTopRated
        movieEntity.isPopular = isPopular
        movieEntity.isUpcoming = isUpcoming
        return movieEntity
    }
}