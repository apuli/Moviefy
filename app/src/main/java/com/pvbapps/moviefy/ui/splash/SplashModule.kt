package com.pvbapps.moviefy.ui.splash

import android.app.Activity
import com.pvbapps.moviefy.ui.utils.RuntimeScoped
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SplashModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @RuntimeScoped
        fun providePresenter(
            splashView: SplashContract.View
        ): SplashContract.Presenter {
            return SplashPresenter(splashView)
        }
    }

    @Binds
    @RuntimeScoped
    abstract fun provideView(splashActivity: SplashActivity): SplashContract.View

    @Binds
    @RuntimeScoped
    abstract fun provideActivity(activity: SplashActivity): Activity
}