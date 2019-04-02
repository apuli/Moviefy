package com.pvbapps.moviefy.ui.main

import android.app.Activity
import com.pvbapps.moviefy.domain.repository.RepositoryModule
import com.pvbapps.moviefy.domain.repository.interfaces.MovieRepository
import com.pvbapps.moviefy.ui.utils.RuntimeScoped
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
abstract class MainModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @RuntimeScoped
        fun providePresenter(
            mainView: MainContract.View,
            movieRepository: MovieRepository
        ): MainContract.Presenter {
            return MainPresenter(mainView, movieRepository)
        }
    }

    @Binds
    @RuntimeScoped
    abstract fun provideView(mainActivity: MainActivity): MainContract.View

    @Binds
    @RuntimeScoped
    abstract fun provideActivity(activity: MainActivity): Activity
}