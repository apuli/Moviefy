package com.pvbapps.moviefy.ui.main

import com.pvbapps.moviefy.domain.model.Movie

interface MainContract {
    interface View {
        fun setToolbarTitle()
        fun addMovie(movie: Movie)
    }

    interface Presenter {
        fun onActivityCreated()
    }
}