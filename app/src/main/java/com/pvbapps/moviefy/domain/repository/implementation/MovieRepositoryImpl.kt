package com.pvbapps.moviefy.domain.repository.implementation

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.offline.MovieOfflineEntity
import com.pvbapps.moviefy.domain.repository.interfaces.MovieRepository
import com.pvbapps.moviefy.domain.response.MoviesResponse
import com.pvbapps.moviefy.infrastructure.IMoviefyServer
import com.pvbapps.moviefy.infrastructure.database.interfaces.DatabaseServer
import com.pvbapps.moviefy.infrastructure.helpers.RetrofitHelper.Companion.API_KEY
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MovieRepositoryImpl(
    val moviefyServer: IMoviefyServer,
    val databaseServer: DatabaseServer
) : MovieRepository {
    override fun getMovies(listId: Int, page: Int): Single<MoviesResponse> =
        moviefyServer.getMovies(listId, page, API_KEY)

    override fun saveMovie(movie: Movie): Observable<*> = databaseServer.saveMovie(movie)

    override fun getMoviesFromDatabase(): Observable<MovieOfflineEntity> = databaseServer.getOfflineMovies()

    override fun deleteOfflineInfo(): Completable = databaseServer.deleteOfflineInfo()
}