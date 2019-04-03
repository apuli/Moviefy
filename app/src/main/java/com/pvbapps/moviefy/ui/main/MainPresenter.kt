package com.pvbapps.moviefy.ui.main

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.model.MovieCategory
import com.pvbapps.moviefy.domain.offline.MovieOfflineEntity
import com.pvbapps.moviefy.domain.repository.interfaces.MovieRepository
import com.pvbapps.moviefy.domain.response.MoviesResponse
import com.pvbapps.moviefy.infrastructure.helpers.ConnectionHelper
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.sql.SQLIntegrityConstraintViolationException

class MainPresenter(
    private val view: MainContract.View,
    private val movieRepository: MovieRepository,
    private val connectionHelper: ConnectionHelper
) : MainContract.Presenter {
    private var moviesCompositeDisposable = CompositeDisposable()

    override fun onActivityCreated() {
        view.setToolbarTitle()
    }

    private fun getMovieSingle(category: MovieCategory): Single<MoviesResponse> =
        when (category) {
            MovieCategory.POPULAR -> movieRepository.getPopularMovies()
            MovieCategory.TOP_RATED -> movieRepository.getTopRatedMovies()
            MovieCategory.UPCOMING -> movieRepository.getUpcomingMovies()
        }

    override fun onCategorySelected(position: Int) {
        val category: MovieCategory = MovieCategory.values()[position]

        if (connectionHelper.isConnected) {
            getMovieSingle(category)
                .map { moviesResponse: MoviesResponse -> moviesResponse.results }
                .toObservable()
                .flatMap { movies: List<Movie> -> Observable.fromIterable(movies) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.clearMovies() }
                .doFinally { getMoviesFromDatabase(category) }
                .subscribe({ movie ->
                    when (category) {
                        MovieCategory.POPULAR -> movie.isPopular = true
                        MovieCategory.TOP_RATED -> movie.isTopRated = true
                        MovieCategory.UPCOMING -> movie.isUpcoming = true
                    }

                    movieRepository.saveMovie(movie)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                        }, { throwable ->
                            if (throwable.cause is SQLIntegrityConstraintViolationException) {
                                movieRepository.updateMovieCategory(movie.id, category)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                    }, { throwable ->
                                        Timber.e(throwable)
                                    })
                            }
                        })
                }, { throwable ->
                    Timber.e(throwable)
                })
                .addTo(moviesCompositeDisposable)
        } else {
            view.clearMovies()
            getMoviesFromDatabase(category)
        }
    }

    private fun getMoviesFromDatabase(category: MovieCategory) {
        movieRepository.getMoviesFromDatabase(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movieOfflineEntity: MovieOfflineEntity ->
                view.addMovie(Movie.getMovieFromDatabaseEntity(movieOfflineEntity))
            }, { throwable ->
                Timber.e(throwable)
            })
    }

    override fun onMovieClicked(movie: Movie) {
        view.showMovieDetailScreen(movie.id)
    }

    override fun onDestroy() {
        moviesCompositeDisposable.dispose()
    }
}
