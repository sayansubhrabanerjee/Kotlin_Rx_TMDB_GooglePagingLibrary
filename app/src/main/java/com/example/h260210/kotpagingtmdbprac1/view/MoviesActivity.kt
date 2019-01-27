package com.example.h260210.kotpagingtmdbprac1.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.h260210.kotpagingtmdbprac1.R
import com.example.h260210.kotpagingtmdbprac1.view.adapter.MoviesAdapter
import com.example.h260210.kotpagingtmdbprac1.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    private val mMoviesViewModel by lazy {
        ViewModelProviders.of(this@MoviesActivity).get(MoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setRecyclerLayout()
        setAdapter()
    }

    private fun setRecyclerLayout() {
        val linearLayoutManager = LinearLayoutManager(this)
        recyler_movies.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyler_movies.layoutManager = linearLayoutManager
    }

    private fun setAdapter() {
        val mMoviesAdapter = MoviesAdapter(this)
        mMoviesViewModel.liveDataMoviesList.observe(this, Observer {
            if (it!!.size == 0) {
                mMoviesAdapter.submitList(it)
            }
        })
        recyler_movies.adapter = mMoviesAdapter
    }
}
