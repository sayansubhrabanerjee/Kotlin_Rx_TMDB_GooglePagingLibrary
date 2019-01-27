package com.example.h260210.kotmovies_prac_1.common

class APIConstants {
    companion object {
        const val MOVIES_BASE_URL = "http://api.themoviedb.org/3/"
        const val MOVIES_POSTER_PATH_BASE_URL = "https://image.tmdb.org/t/p/w92/"
        const val MOVIES_AUTH_KEY = "<your auth key from TMDB site for API Call>"

        const val INITIAL_PAGE = 1
        const val LIST_PAGE_SIZE = 20
        const val LIST_INITIAL_LOAD_SIZE_HINT = 60
    }
}