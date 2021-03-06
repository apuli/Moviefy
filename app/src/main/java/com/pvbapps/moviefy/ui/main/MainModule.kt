package com.pvbapps.moviefy.ui.main

import android.app.Activity
import com.pvbapps.moviefy.domain.repository.RepositoryModule
import com.pvbapps.moviefy.domain.repository.interfaces.MovieRepository
import com.pvbapps.moviefy.infrastructure.NetworkingModule
import com.pvbapps.moviefy.infrastructure.helpers.ConnectionHelper
import com.pvbapps.moviefy.ui.adapters.MovieAdapter
import com.pvbapps.moviefy.ui.utils.RuntimeScoped
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        RepositoryModule::class,
        NetworkingModule::class
    ]
)
abstract class MainModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @RuntimeScoped
        fun providePresenter(
            mainView: MainContract.View,
            movieRepository: MovieRepository,
            connectionHelper: ConnectionHelper
        ): MainContract.Presenter {
            return MainPresenter(mainView, movieRepository, connectionHelper)
        }

        @JvmStatic
        @Provides
        @RuntimeScoped
        fun provideMovieListener(activity: MainActivity) = activity as MovieAdapter.MovieListener
    }

    @Binds
    @RuntimeScoped
    abstract fun provideView(mainActivity: MainActivity): MainContract.View

    @Binds
    @RuntimeScoped
    abstract fun provideActivity(activity: MainActivity): Activity
}