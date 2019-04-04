package com.pvbapps.moviefy.ui.movie

import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.model.MovieDetail
import com.pvbapps.moviefy.domain.offline.MovieOfflineEntity
import com.pvbapps.moviefy.domain.repository.interfaces.MovieRepository
import com.pvbapps.moviefy.infrastructure.helpers.ConnectionHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MoviePresenter(
    private val view: MovieContract.View,
    private val movieRepository: MovieRepository,
    private val connectionHelper: ConnectionHelper
) : MovieContract.Presenter {

    private var movieCompositeDisposable = CompositeDisposable()

    override fun onActivityCreated(movieId: Int) {
        view.showToolbarTitle()

        if (connectionHelper.isConnected) {
            movieRepository.getMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showProgress() }
                .doFinally { view.hideProgress() }
                .subscribe({ movieDetail: MovieDetail ->
                    view.showMovie(movieDetail)
                }, { throwable -> Timber.e(throwable) })
        } else {
            movieRepository.getOfflineMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showProgress() }
                .doFinally { view.hideProgress() }
                .subscribe({ movieOffline: MovieOfflineEntity ->
                    view.showMovie(Movie.getMovieFromDatabaseEntity(movieOffline))
                }, { throwable -> Timber.e(throwable) })
        }
    }

    override fun onDestroy() {
        movieCompositeDisposable.dispose()
    }
}