package com.pvbapps.moviefy.infrastructure

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.pvbapps.moviefy.application.MoviefyApp
import com.pvbapps.moviefy.infrastructure.helpers.ConnectionHelper
import com.pvbapps.moviefy.infrastructure.helpers.RetrofitHelper
import dagger.Module
import dagger.Provides

@Module
class NetworkingModule {

    @Provides
    fun provideRetrofitManager(
        application: MoviefyApp,
        gson: Gson
    ): RetrofitHelper =
        RetrofitHelper(application, gson)

    @Provides
    fun provideIMoviefyServer(retrofitHelper: RetrofitHelper): IMoviefyServer {
        return retrofitHelper.retrofit?.create(IMoviefyServer::class.java)!!
    }

    @Provides
    fun providesConnectionHelper(application: Application): ConnectionHelper {
        return ConnectionHelper(application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }
}