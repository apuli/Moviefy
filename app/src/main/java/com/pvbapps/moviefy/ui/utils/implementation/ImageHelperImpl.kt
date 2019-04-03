package com.pvbapps.moviefy.ui.utils.implementation

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pvbapps.moviefy.application.MoviefyApp
import com.pvbapps.moviefy.ui.utils.interfaces.ImageHelper

class ImageHelperImpl(val context: MoviefyApp) : ImageHelper {

    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    override fun loadImage(path: String, imageView: ImageView) {
        Glide
            .with(context)
            .load("$IMAGE_BASE_URL$path")
            .into(imageView)
    }
}