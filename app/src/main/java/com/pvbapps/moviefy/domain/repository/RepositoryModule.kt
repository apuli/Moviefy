package com.pvbapps.moviefy.domain.repository

import com.pvbapps.moviefy.domain.repository.implementation.MovieRepositoryImpl
import com.pvbapps.moviefy.domain.repository.interfaces.MovieRepository
import com.pvbapps.moviefy.infrastructure.IMoviefyServer
import com.pvbapps.moviefy.infrastructure.NetworkingModule
import com.pvbapps.moviefy.infrastructure.database.DatabaseModule
import com.pvbapps.moviefy.infrastructure.database.interfaces.DatabaseServer
import dagger.Module
import dagger.Provides

@Module(includes = [
        NetworkingModule::class,
        DatabaseModule::class
])
class RepositoryModule {

    @Provides
    fun provideMovieRepository(
        moviefyServer: IMoviefyServer,
        databaseServer: DatabaseServer
    ): MovieRepository = MovieRepositoryImpl(moviefyServer, databaseServer)
}