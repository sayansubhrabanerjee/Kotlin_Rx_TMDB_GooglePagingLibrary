package com.example.h260210.kotmovies_prac_1.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.h260210.kotlinmovies.model.Movie
import com.example.h260210.kotpagingtmdbprac1.R

class CommonUtils {

    companion object {

        fun checkRatings(voteAverage: Double): Int {
            return when (voteAverage) {
                in 7.0..10.0 -> R.drawable.ic_star_most_popular
                6.0 -> R.drawable.ic_star_average_popular
                else -> R.drawable.ic_star_least_popular
            }
        }

        fun configureGlide(imageView: ImageView, movie: Movie, context: Context) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)

            Glide
                .with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(APIConstants.MOVIES_POSTER_PATH_BASE_URL + movie.posterPath)
                .into(imageView)
        }

        fun getMoviesAPIKey(): String = APIConstantsKey.MOVIES_AUTH_KEY
    }
}