package com.pvbapps.moviefy.ui.movie

import android.app.Activity
import com.pvbapps.moviefy.domain.repository.RepositoryModule
import com.pvbapps.moviefy.domain.repository.interfaces.MovieRepository
import com.pvbapps.moviefy.infrastructure.NetworkingModule
import com.pvbapps.moviefy.infrastructure.helpers.ConnectionHelper
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
abstract class MovieModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @RuntimeScoped
        fun providePresenter(
            movieView: MovieContract.View,
            movieRepository: MovieRepository,
            connectionHelper: ConnectionHelper
        ): MovieContract.Presenter {
            return MoviePresenter(movieView, movieRepository, connectionHelper)
        }
    }

    @Binds
    @RuntimeScoped
    abstract fun provideView(movieActivity: MovieActivity): MovieContract.View

    @Binds
    @RuntimeScoped
    abstract fun provideActivity(activity: MovieActivity): Activity
}