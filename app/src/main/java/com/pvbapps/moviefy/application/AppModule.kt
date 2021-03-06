package com.pvbapps.moviefy.application

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pvbapps.moviefy.infrastructure.gson.AnnotationExclusionStrategy
import com.pvbapps.moviefy.ui.utils.implementation.ImageHelperImpl
import com.pvbapps.moviefy.ui.utils.interfaces.ImageHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(application: MoviefyApp): Context = application.applicationContext

    @Provides
    fun providesGson(): Gson {
        // TODO Check ISO FORMAT
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
            .setExclusionStrategies(AnnotationExclusionStrategy())
            .create()
    }

    @Provides
    fun providesImageHelper(context: MoviefyApp): ImageHelper = ImageHelperImpl(context)
}