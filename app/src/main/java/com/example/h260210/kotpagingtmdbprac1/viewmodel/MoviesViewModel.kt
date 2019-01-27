package com.example.h260210.kotpagingtmdbprac1.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import com.example.h260210.kotlinmovies.model.Movie
import com.example.h260210.kotmovies_prac_1.common.APIConstants
import com.example.h260210.kotpagingtmdbprac1.model.factory.MoviesDataSourceFactory

class MoviesViewModel : ViewModel() {

    var liveDataMoviesList: LiveData<PagedList<Movie>>
    private var liveDataSource: LiveData<PageKeyedDataSource<Int, Movie>>

    init {
        val factory = MoviesDataSourceFactory()
        liveDataSource = factory.mutableLiveDataSource

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(APIConstants.LIST_INITIAL_LOAD_SIZE_HINT)
            .setPageSize(APIConstants.LIST_PAGE_SIZE)
            .build()

        liveDataMoviesList = LivePagedListBuilder(factory, pagedListConfig).build()
    }
}