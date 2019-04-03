package com.pvbapps.moviefy.ui.adapters.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.ui.adapters.MovieAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

class MovieViewHolder(
    override val containerView: View,
    val listener: MovieAdapter.MovieListener
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(movie: Movie) {
        movie_title.text = movie.title
        movie_overview.text = movie.overview

        movie_layout.setOnClickListener { listener.onMovieClick(movie) }
    }
}