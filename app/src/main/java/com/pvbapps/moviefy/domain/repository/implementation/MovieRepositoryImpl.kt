package com.pvbapps.moviefy.domain.repository.implementation

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.model.MovieCategory
import com.pvbapps.moviefy.domain.model.MovieDetail
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
    private val moviefyServer: IMoviefyServer,
    private val databaseServer: DatabaseServer
) : MovieRepository {

    override fun getMovies(listId: Int, page: Int): Single<MoviesResponse> =
        moviefyServer.getMovies(listId, page, API_KEY)

    override fun saveMovie(movie: Movie): Observable<*> = databaseServer.saveMovie(movie)

    override fun getMoviesFromDatabase(category: MovieCategory): Observable<MovieOfflineEntity> =
        databaseServer.getOfflineMovies(category)

    override fun deleteOfflineInfo(): Completable = databaseServer.deleteOfflineInfo()

    override fun getTopRatedMovies(): Single<MoviesResponse> = moviefyServer.getTopRatedMovies(API_KEY)

    override fun getPopularMovies(): Single<MoviesResponse> = moviefyServer.getPopularMovies(API_KEY)

    override fun getUpcomingMovies(): Single<MoviesResponse> = moviefyServer.getUpcomingMovies(API_KEY)

    override fun updateMovieCategory(movieId: Int, category: MovieCategory): Completable =
        databaseServer.updateMovieCategory(movieId, category)

    override fun getOfflineMovie(movieId: Int): Observable<MovieOfflineEntity> =
        databaseServer.getMovie(movieId)

    override fun getMovie(movieId: Int): Single<MovieDetail> = moviefyServer.getMovie(movieId, API_KEY)

    override fun searchMovie(query: String): Single<MoviesResponse> = moviefyServer.searchMovie(API_KEY, query)
}