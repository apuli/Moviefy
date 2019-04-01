package com.pvbapps.moviefy.application

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(application: MoviefyApp): Context = application.applicationContext
}