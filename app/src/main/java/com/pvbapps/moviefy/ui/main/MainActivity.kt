package com.pvbapps.moviefy.ui.main

import android.os.Bundle
import com.pvbapps.moviefy.R
import dagger.android.DaggerActivity

class MainActivity : DaggerActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
