package com.pvbapps.moviefy.ui.movie

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.model.MovieDetail

interface MovieContract {
    interface View {
        fun showToolbarTitle()
        fun showMovie(movie: Movie)
        fun showMovie(movieDetail: MovieDetail)
    }

    interface Presenter {
        fun onActivityCreated(movieId: Int)
        fun onDestroy()
    }
}