package com.pvbapps.moviefy.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MoviefyApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}