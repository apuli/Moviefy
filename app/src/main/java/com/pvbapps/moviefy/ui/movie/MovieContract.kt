package com.pvbapps.moviefy.ui.movie

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.model.MovieDetail

interface MovieContract {
    interface View {
        fun showToolbarTitle()
        fun showMovie(movie: Movie)
        fun showMovie(movieDetail: MovieDetail)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onActivityCreated(movieId: Int)
        fun onDestroy()
    }
}