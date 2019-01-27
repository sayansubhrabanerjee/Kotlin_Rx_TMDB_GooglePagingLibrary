package com.example.h260210.kotpagingtmdbprac1.view.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.h260210.kotlinmovies.model.Movie
import com.example.h260210.kotmovies_prac_1.common.CommonUtils
import com.example.h260210.kotpagingtmdbprac1.R
import com.example.h260210.kotpagingtmdbprac1.view.extensions.showToast
import kotlinx.android.synthetic.main.list_movies.view.*

class MoviesAdapter(private val context: Context) :
    PagedListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_movies, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(moviesViewHolder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        moviesViewHolder.bindTo(movie, position)
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var selectedItem: Movie? = null
        var selectedPosition: Int = -1

        init {
            itemView.setOnClickListener {
                context.showToast("Movie: ${selectedItem!!.title} , position: ${selectedPosition + 1}")
            }
        }

        fun bindTo(movie: Movie?, position: Int) {
            movie?.let {
                CommonUtils.configureGlide(itemView.imageView_poster, it, context)
                itemView.textView_title.text = it.title
                itemView.textView_desc.text = it.overview
                CommonUtils.checkRatings(it.voteAverage!!)

                this.selectedItem = movie
                this.selectedPosition = position
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
        }
    }
}
