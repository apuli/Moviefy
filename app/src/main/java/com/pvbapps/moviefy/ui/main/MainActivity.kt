package com.pvbapps.moviefy.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pvbapps.moviefy.R
import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.ui.adapters.MovieAdapter
import com.pvbapps.moviefy.ui.hideKeyboard
import com.pvbapps.moviefy.ui.movie.MovieActivity
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_view.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : DaggerActivity(), MainContract.View, MovieAdapter.MovieListener {

    @Inject
    lateinit var mainPresenter: MainContract.Presenter

    @Inject
    lateinit var movieAdapter: MovieAdapter

    companion object {
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        mainPresenter.onActivityCreated()
    }

    private fun initUI() {
        ArrayAdapter.createFromResource(
            this,
            R.array.categories,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.item_category)
            categories_spinner.adapter = adapter
        }

        categories_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mainPresenter.onCategorySelected(position)
            }
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        movies_recyclerView.layoutManager = layoutManager

        movies_recyclerView.addItemDecoration(
            DividerItemDecoration(movies_recyclerView.context, layoutManager.orientation)
        )
        movies_recyclerView.adapter = movieAdapter

        search_icon.setOnClickListener { mainPresenter.searchMovie(search_movie.text.toString()) }
    }

    override fun addMovie(movie: Movie) {
        movieAdapter.putItem(movie)
    }

    override fun hideActivityKeyboard() {
        hideKeyboard()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    override fun onMovieClick(movie: Movie) {
        mainPresenter.onMovieClicked(movie)
    }

    override fun setToolbarTitle() {
        toolbar.title = getString(R.string.app_name)

        setActionBar(toolbar)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun clearMovies() {
        movieAdapter.clearItems()
    }

    override fun showMovieDetailScreen(movieId: Int) {
        startActivity(MovieActivity.newInstance(this, movieId))
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }
}
