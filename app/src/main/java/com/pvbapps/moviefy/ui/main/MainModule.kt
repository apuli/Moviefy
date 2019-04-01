package com.pvbapps.moviefy.ui.main

import android.app.Activity
import com.pvbapps.moviefy.ui.utils.RuntimeScoped
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MainModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @RuntimeScoped
        fun providePresenter(
            mainView: MainContract.View
        ): MainContract.Presenter {
            return MainPresenter(mainView)
        }
    }

    @Binds
    @RuntimeScoped
    abstract fun provideView(mainActivity: MainActivity): MainContract.View

    @Binds
    @RuntimeScoped
    abstract fun provideActivity(activity: MainActivity): Activity
}