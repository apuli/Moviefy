package com.pvbapps.moviefy.ui.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.pvbapps.moviefy.R
import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.domain.model.MovieDetail
import com.pvbapps.moviefy.ui.utils.DateUtils
import com.pvbapps.moviefy.ui.utils.interfaces.ImageHelper
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.progress_view.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MovieActivity : DaggerActivity(), MovieContract.View {

    @Inject
    lateinit var moviePresenter: MovieContract.Presenter
    @Inject
    lateinit var imageHelper: ImageHelper

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
        fun newInstance(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        moviePresenter.onActivityCreated(intent.getIntExtra(EXTRA_MOVIE_ID, -1))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    override fun showToolbarTitle() {
        toolbar.title = getString(R.string.movieDetail_title)

        setActionBar(toolbar)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
    }

    override fun showMovie(movie: Movie) {
        movieDetail_title.text = movie.title
        movieDetail_overview.text = movie.overview
        movieDetail_tagline.visibility = View.GONE
        movieDetail_genres.visibility = View.GONE
        movieDetail_releaseDate.text =
            "${getString(R.string.movieDetail_ReleaseDate)} ${DateUtils.getMovieFormatDateString(movie.releaseDate)}"

        imageHelper.loadImage(movie.posterPath, movieDetail_image)
    }

    override fun showMovie(movieDetail: MovieDetail) {
        movieDetail_title.text = movieDetail.title
        movieDetail_overview.text = movieDetail.overview
        movieDetail_tagline.text = movieDetail.tagline
        movieDetail_releaseDate.text =
            "${getString(R.string.movieDetail_ReleaseDate)} ${DateUtils.getMovieFormatDateString(movieDetail.releaseDate)}"

        var genres = String()
        movieDetail.genres.forEach { genres = "$genres $it," }
        movieDetail_genres.text = "${getString(R.string.movieDetail_genres)} $genres"

        imageHelper.loadImage(movieDetail.posterPath, movieDetail_image)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        moviePresenter.onDestroy()
        super.onDestroy()
    }
}