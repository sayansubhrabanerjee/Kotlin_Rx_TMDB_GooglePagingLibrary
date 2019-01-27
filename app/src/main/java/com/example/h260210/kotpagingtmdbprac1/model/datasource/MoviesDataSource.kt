package com.example.h260210.kotpagingtmdbprac1.model.datasource

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.example.h260210.kotlinmovies.model.Movie
import com.example.h260210.kotmovies_prac_1.common.APIConstants
import com.example.h260210.kotmovies_prac_1.common.APIConstantsKey
import com.example.h260210.kotpagingtmdbprac1.model.service.IMoviesService
import com.example.h260210.kotpagingtmdbprac1.model.service.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesDataSource : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        serviceClass.getTopRatedMovies(
            APIConstants.INITIAL_PAGE,
            params.requestedLoadSize,
            APIConstantsKey.MOVIES_AUTH_KEY
        ).subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it.moviesList!!,null,APIConstants.INITIAL_PAGE + 1)
            },{
                Log.i(TAG,"mytest: loadInitial: Error: ${it.message}")
            })!!
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        serviceClass.getTopRatedMovies(params.key, params.requestedLoadSize, APIConstantsKey.MOVIES_AUTH_KEY)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val key = if (it.page!! < it.totalPages!!) params.key + 1 else null
                callback.onResult(it.moviesList!!,key)
            },{
                Log.i(TAG,"mytest: loadAfter: Error: ${it.message}")
            })!!
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        serviceClass.getTopRatedMovies(params.key, params.requestedLoadSize, APIConstantsKey.MOVIES_AUTH_KEY)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val key = if (params.key > 1) params.key - 1 else null
                callback.onResult(it.moviesList!!,key)
            },{
                Log.i(TAG,"mytest: loadAfter: Error: ${it.message}")
            })!!
    }

    companion object {
        val TAG: String = MoviesDataSource::class.java.simpleName
        val serviceClass = ServiceGenerator.createService(IMoviesService::class.java)
    }
}