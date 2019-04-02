package com.pvbapps.moviefy.infrastructure

import com.pvbapps.moviefy.domain.response.MoviesResponse
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
}