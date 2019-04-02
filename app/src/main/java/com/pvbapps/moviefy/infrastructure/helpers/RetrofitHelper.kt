package com.pvbapps.moviefy.infrastructure.helpers

import android.app.Application
import com.google.gson.Gson
import com.pvbapps.moviefy.infrastructure.RxErrorHandlingCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitHelper(
    private val application: Application,
    private val gson: Gson
) {
    private var customOkHttpClient: OkHttpClient? = null

    companion object {
        const val API_KEY = "910596b5aa798a0d4d9375b4764fb26c"
        const val BASE_URL = "https://api.themoviedb.org/4/"
    }

    val retrofit: Retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .cache(cache)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)

        customOkHttpClient = httpClient.build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .client(customOkHttpClient!!)
            .build()
    }

    private val cache: Cache by lazy {
        Cache(File(application.cacheDir, "http-cache"), (10 * 1024 * 1024).toLong())
    }
}