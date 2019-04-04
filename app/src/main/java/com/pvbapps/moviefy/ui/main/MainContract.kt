package com.pvbapps.moviefy.ui.main

import com.pvbapps.moviefy.domain.model.Movie

interface MainContract {
    interface View {
        fun setToolbarTitle()
        fun addMovie(movie: Movie)
        fun clearMovies()
        fun showMovieDetailScreen(movieId: Int)
        fun hideActivityKeyboard()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onActivityCreated()
        fun onCategorySelected(position: Int)
        fun onDestroy()
        fun onMovieClicked(movie: Movie)
        fun searchMovie(query: String)
    }
}