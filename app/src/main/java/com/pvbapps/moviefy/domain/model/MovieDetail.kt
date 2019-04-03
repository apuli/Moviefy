package com.pvbapps.moviefy.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val tagline: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    val title: String,
    val runtime: Int,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>
)