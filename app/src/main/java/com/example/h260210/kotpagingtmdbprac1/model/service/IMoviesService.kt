package com.example.h260210.kotpagingtmdbprac1.model.service

import com.example.h260210.kotlinmovies.model.MoviesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") pageNumber: Int,
        @Query("total_pages") pageSize: Int,
        @Query("api_key") apiKey: String
    ): Flowable<MoviesResponse>
}