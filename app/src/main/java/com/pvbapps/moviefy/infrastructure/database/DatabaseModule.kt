package com.pvbapps.moviefy.infrastructure.database

import com.pvbapps.moviefy.application.MoviefyApp
import com.pvbapps.moviefy.domain.offline.Models
import com.pvbapps.moviefy.infrastructure.database.implementation.RequeryServerImpl
import com.pvbapps.moviefy.infrastructure.database.interfaces.DatabaseServer
import dagger.Module
import dagger.Provides
import io.requery.Persistable
import io.requery.android.sqlite.DatabaseSource
import io.requery.reactivex.KotlinReactiveEntityStore
import io.requery.sql.KotlinEntityDataStore
import io.requery.sql.TableCreationMode

@Module
class DatabaseModule {

    @Provides
    fun provideReactiveEntityStore(
        application: MoviefyApp
    ): KotlinReactiveEntityStore<Persistable> {
        val source = DatabaseSource(application, Models.DEFAULT, 1)
        source.setTableCreationMode(TableCreationMode.DROP_CREATE)
        return KotlinReactiveEntityStore(KotlinEntityDataStore(source.configuration))
    }

    @Provides
    fun provideDatabaseServer(
        entityStore: KotlinReactiveEntityStore<Persistable>
    ): DatabaseServer = RequeryServerImpl(entityStore)
}