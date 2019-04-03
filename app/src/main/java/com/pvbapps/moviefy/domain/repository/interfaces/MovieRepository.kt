package com.pvbapps.moviefy.domain.repository.interfaces

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.model.MovieCategory
import com.pvbapps.moviefy.domain.model.MovieDetail
import com.pvbapps.moviefy.domain.offline.MovieOfflineEntity
import com.pvbapps.moviefy.domain.response.MoviesResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRepository {

    fun getMovies(listId: Int, page: Int): Single<MoviesResponse>
    fun saveMovie(movie: Movie): Observable<*>
    fun getMoviesFromDatabase(category: MovieCategory): Observable<MovieOfflineEntity>
    fun deleteOfflineInfo(): Completable
    fun getTopRatedMovies(): Single<MoviesResponse>
    fun getPopularMovies(): Single<MoviesResponse>
    fun getUpcomingMovies(): Single<MoviesResponse>
    fun updateMovieCategory(movieId: Int, category: MovieCategory): Completable
    fun getOfflineMovie(movieId: Int): Observable<MovieOfflineEntity>
    fun getMovie(movieId: Int): Single<MovieDetail>
}