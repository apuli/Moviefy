package com.pvbapps.moviefy.ui.main

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.offline.MovieOfflineEntity
import com.pvbapps.moviefy.domain.repository.interfaces.MovieRepository
import com.pvbapps.moviefy.domain.response.MoviesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainPresenter(
    private val view: MainContract.View,
    private val movieRepository: MovieRepository
) : MainContract.Presenter {
    private var moviesCompositeDisposable = CompositeDisposable()

    init {
        movieRepository.getMovies(1, 1)
            .map { moviesResponse: MoviesResponse -> moviesResponse.results }
            .toObservable()
            .flatMap { movies: List<Movie> -> Observable.fromIterable(movies) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onMoviesRetrieved() }
            .doFinally { onMoviesSaved() }
            .subscribe({ movie ->
                movieRepository.saveMovie(movie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }, { throwable ->
                Timber.e(throwable)
            })
            .addTo(moviesCompositeDisposable)
    }

    override fun onActivityCreated() {
        view.setToolbarTitle()
    }

    private fun onMoviesSaved() {
        movieRepository.getMoviesFromDatabase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movieOfflineEntity: MovieOfflineEntity ->
                view.addMovie(Movie.getMovieFromDatabaseEntity(movieOfflineEntity))
            }, { throwable ->
                Timber.e(throwable)
            })
    }

    private fun onMoviesRetrieved() {
        movieRepository.deleteOfflineInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("Movies deleted")
            }, { throwable ->
                Timber.e(throwable)
            })
    }
}