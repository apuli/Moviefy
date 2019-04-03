package com.pvbapps.moviefy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pvbapps.moviefy.R
import com.pvbapps.moviefy.domain.model.Movie
import com.pvbapps.moviefy.ui.adapters.viewHolders.MovieViewHolder
import com.pvbapps.moviefy.ui.utils.RuntimeScoped
import com.pvbapps.moviefy.ui.utils.interfaces.ImageHelper
import javax.inject.Inject

@RuntimeScoped
class MovieAdapter @Inject constructor(
    private val listener: MovieListener,
    private val imageHelper: ImageHelper
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<Movie>()

    interface MovieListener {
        fun onMovieClick(movie: Movie)
    }

    fun putItem(item: Movie) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    override fun getItemCount() = items.size

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.item_movie, parent, false), listener, imageHelper)
    }
}
