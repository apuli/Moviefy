package com.pvbapps.moviefy.infrastructure.database.interfaces

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.offline.MovieOfflineEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface DatabaseServer {
    fun saveMovie(movie: Movie): Observable<*>
    fun deleteAllMovies()
    fun getOfflineMovies(): Observable<MovieOfflineEntity>
    fun deleteOfflineInfo(): Completable
}