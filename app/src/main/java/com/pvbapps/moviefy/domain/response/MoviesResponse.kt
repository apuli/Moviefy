package com.pvbapps.moviefy.domain.response

import com.google.gson.annotations.SerializedName
import com.pvbapps.moviefy.domain.model.Movie

class MoviesResponse(
    val id: Int,
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val name: String,
    val public: Boolean,
    val results: List<Movie>
)