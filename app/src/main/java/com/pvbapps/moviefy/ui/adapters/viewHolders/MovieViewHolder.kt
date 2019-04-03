package com.pvbapps.moviefy.ui.adapters.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.ui.adapters.MovieAdapter
import com.pvbapps.moviefy.ui.utils.interfaces.ImageHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

class MovieViewHolder(
    override val containerView: View,
    private val listener: MovieAdapter.MovieListener,
    private val imageHelper: ImageHelper
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(movie: Movie) {
        movie_title.text = movie.title
        movie_overview.text = movie.overview

        imageHelper.loadImage(movie.posterPath, movie_image)
        movie_layout.setOnClickListener { listener.onMovieClick(movie) }
    }
}