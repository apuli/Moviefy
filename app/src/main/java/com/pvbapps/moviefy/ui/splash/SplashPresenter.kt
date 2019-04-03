package com.pvbapps.moviefy.ui.splash

class SplashPresenter(private val view: SplashContract.View) : SplashContract.Presenter {
    override fun onLogoAnimationEnd() {
        view.showMoviesScreen()
    }
}