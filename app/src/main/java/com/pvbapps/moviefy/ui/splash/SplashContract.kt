package com.pvbapps.moviefy.ui.splash

class SplashContract {
    interface View {
        fun showMoviesScreen()
    }

    interface Presenter {
        fun onLogoAnimationEnd()
    }
}