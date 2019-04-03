package com.pvbapps.moviefy.application

import com.pvbapps.moviefy.domain.repository.RepositoryModule
import com.pvbapps.moviefy.infrastructure.NetworkingModule
import com.pvbapps.moviefy.infrastructure.database.DatabaseModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        BindingModule::class,
        NetworkingModule::class,
        RepositoryModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<MoviefyApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MoviefyApp>()
}