package com.pvbapps.moviefy.domain.model

data class Genre(
    val id: Int,
    val name: String
) {
    override fun toString() = name
}