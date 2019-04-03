package com.pvbapps.moviefy.infrastructure.database.implementation

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.model.MovieCategory
import com.pvbapps.moviefy.domain.offline.MovieOffline
import com.pvbapps.moviefy.domain.offline.MovieOfflineEntity
import com.pvbapps.moviefy.infrastructure.database.interfaces.DatabaseServer
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.rxkotlin.toCompletable
import io.requery.Persistable
import io.requery.kotlin.eq
import io.requery.reactivex.KotlinReactiveEntityStore

class RequeryServerImpl(val entityStore: KotlinReactiveEntityStore<Persistable>) : DatabaseServer {

    override fun saveMovie(movie: Movie): Observable<*> {
        return entityStore.insert(movie.toDatabaseEntity()).toObservable()
    }

    override fun deleteAllMovies() {
        entityStore delete (MovieOffline::class)
    }

    override fun getOfflineMovies(category: MovieCategory): Observable<MovieOfflineEntity> =
        when (category) {
            MovieCategory.TOP_RATED ->
                (entityStore select (MovieOfflineEntity::class)
                    where (MovieOfflineEntity::isTopRated eq true)).get().observable()
            MovieCategory.UPCOMING ->
                (entityStore select (MovieOfflineEntity::class)
                    where (MovieOfflineEntity::isUpcoming eq true)).get().observable()
            MovieCategory.POPULAR ->
                (entityStore select (MovieOfflineEntity::class)
                    where (MovieOfflineEntity::isPopular eq true)).get().observable()
        }

    override fun deleteOfflineInfo(): Completable =
        entityStore.delete(MovieOfflineEntity::class).get().toCompletable()

    override fun updateMovieCategory(movieId: Int, category: MovieCategory): Completable =
        when (category) {
            MovieCategory.TOP_RATED ->
                entityStore.update(MovieOfflineEntity::class)
                    .set(MovieOfflineEntity.TOP_RATED, true)
                    .where(MovieOfflineEntity.ID.eq(movieId)).get().toCompletable()
            MovieCategory.UPCOMING ->
                entityStore.update(MovieOfflineEntity::class)
                    .set(MovieOfflineEntity.UPCOMING, true)
                    .where(MovieOfflineEntity.ID.eq(movieId)).get().toCompletable()
            MovieCategory.POPULAR ->
                entityStore.update(MovieOfflineEntity::class)
                    .set(MovieOfflineEntity.POPULAR, true)
                    .where(MovieOfflineEntity.ID.eq(movieId)).get().toCompletable()
        }
}