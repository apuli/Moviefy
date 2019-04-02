package com.pvbapps.moviefy.infrastructure.database.implementation

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.offline.MovieOffline
import com.pvbapps.moviefy.domain.offline.MovieOfflineEntity
import com.pvbapps.moviefy.infrastructure.database.interfaces.DatabaseServer
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.rxkotlin.toCompletable
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore

class RequeryServerImpl(val entityStore: KotlinReactiveEntityStore<Persistable>) : DatabaseServer {

    override fun saveMovie(movie: Movie): Observable<*> {
        return entityStore.insert(movie.toDatabaseEntity()).toObservable()
    }

    override fun deleteAllMovies() {
        entityStore delete (MovieOffline::class)
    }

    override fun getOfflineMovies(): Observable<MovieOfflineEntity> =
        (entityStore select (MovieOfflineEntity::class)).get().observable()

    override fun deleteOfflineInfo(): Completable =
        entityStore.delete(MovieOfflineEntity::class).get().toCompletable()
}