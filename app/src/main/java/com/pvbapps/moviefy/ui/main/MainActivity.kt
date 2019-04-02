package com.pvbapps.moviefy.ui.main

import android.os.Bundle
import com.pvbapps.moviefy.R
import dagger.android.DaggerActivity
import javax.inject.Inject

class MainActivity : DaggerActivity(), MainContract.View {

    @Inject lateinit var mainPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
