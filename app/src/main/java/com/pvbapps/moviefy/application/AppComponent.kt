package com.pvbapps.moviefy.application

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    BindingModule::class
])
interface AppComponent : AndroidInjector<MoviefyApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MoviefyApp>()
}