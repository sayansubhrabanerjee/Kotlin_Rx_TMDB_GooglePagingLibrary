package com.example.h260210.kotpagingtmdbprac1.model.factory

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.example.h260210.kotlinmovies.model.Movie
import com.example.h260210.kotpagingtmdbprac1.model.datasource.MoviesDataSource

class MoviesDataSourceFactory : DataSource.Factory<Int, Movie>() {

    var mutableLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, Movie>> = MutableLiveData()

    override fun create(): DataSource<Int, Movie> {
        val dataSource = MoviesDataSource()
        mutableLiveDataSource.postValue(dataSource)
        return dataSource
    }
}