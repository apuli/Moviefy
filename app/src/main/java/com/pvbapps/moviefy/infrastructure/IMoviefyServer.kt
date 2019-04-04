package com.pvbapps.moviefy.infrastructure

import com.pvbapps.moviefy.domain.model.MovieDetail
import com.pvbapps.moviefy.infrastructure.response.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMoviefyServer {

    @GET("list/{list_id}")
    fun getMovies(
        @Path("list_id") listId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Single<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String
    ): Single<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Single<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String
    ): Single<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<MovieDetail>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Single<MoviesResponse>
}