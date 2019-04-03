package com.pvbapps.moviefy.application

import com.pvbapps.moviefy.ui.main.MainActivity
import com.pvbapps.moviefy.ui.main.MainModule
import com.pvbapps.moviefy.ui.movie.MovieActivity
import com.pvbapps.moviefy.ui.movie.MovieModule
import com.pvbapps.moviefy.ui.utils.RuntimeScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @RuntimeScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity

    @RuntimeScoped
    @ContributesAndroidInjector(modules = [MovieModule::class])
    abstract fun movieActivity(): MovieActivity
}