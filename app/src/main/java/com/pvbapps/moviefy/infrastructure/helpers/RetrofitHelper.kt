package com.pvbapps.moviefy.infrastructure.helpers

import android.app.Application
import com.google.gson.Gson
import com.pvbapps.moviefy.infrastructure.RxErrorHandlingCallAdapterFactory
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
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
        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val HEADER_PRAGMA = "Pragma"
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

//    private fun provideCacheInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            val response = chain.proceed(chain.request())
//
//            val cacheControl: CacheControl = if (internetManager.isOnline) {
//                CacheControl.Builder()
//                    .maxAge(0, TimeUnit.SECONDS)
//                    .build()
//            } else {
//                CacheControl.Builder()
//                    .maxStale(7, TimeUnit.DAYS)
//                    .build()
//            }
//
//            response.newBuilder()
//                .removeHeader(HEADER_PRAGMA)
//                .removeHeader(HEADER_CACHE_CONTROL)
//                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
//                .build()
//        }
//    }

//    private fun provideOfflineCacheInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            var request = chain.request()
//
//            if (!internetManager.isOnline) {
//                val cacheControl = CacheControl.Builder()
//                    .maxStale(7, TimeUnit.DAYS)
//                    .build()
//
//                request = request.newBuilder()
//                    .removeHeader(HEADER_PRAGMA)
//                    .removeHeader(HEADER_CACHE_CONTROL)
//                    .cacheControl(cacheControl)
//                    .build()
//            }
//
//            chain.proceed(request)
//        }
//    }

    private fun provideForcedOfflineCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            val cacheControl = CacheControl.Builder()
                .maxStale(7, TimeUnit.DAYS)
                .build()

            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()

            chain.proceed(request)
        }
    }

//    fun clean() {
//        if (customOkHttpClient != null) {
//            // Cancel Pending Request
//            customOkHttpClient!!.dispatcher().cancelAll()
//        }
//
//        if (customCachedOkHttpClient != null) {
//            // Cancel Pending Request
//            customCachedOkHttpClient!!.dispatcher().cancelAll()
//        }
//
//        if (defaultOkHttpClient != null) {
//            // Cancel Pending Request
//            defaultOkHttpClient!!.dispatcher().cancelAll()
//        }
//
//        try {
//            cache.evictAll()
//        } catch (e: IOException) {
//            Timber.e("Error cleaning http cache")
//        }
//    }

//    private fun provideHeaderInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            val request = chain.request()
//
//            val accessToken = sessionRepository.getAccessToken().onErrorReturnItem("").blockingGet()
//
//            val newRequest = if (!accessToken.isNullOrEmpty()) {
//                request.newBuilder()
//                    .addHeader(RestConstants.HEADER_AUTH, accessToken)
//                    .build()
//            } else {
//                request
//            }
//
//            chain.proceed(newRequest)
//        }
//    }
}