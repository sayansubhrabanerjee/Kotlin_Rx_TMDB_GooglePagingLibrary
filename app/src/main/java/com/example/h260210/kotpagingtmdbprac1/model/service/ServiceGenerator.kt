package com.example.h260210.kotpagingtmdbprac1.model.service

import com.example.h260210.kotmovies_prac_1.common.APIConstants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null

        private fun getInstance(): Retrofit? {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildRetrofit().also { INSTANCE = it }
            }
        }

        private fun buildRetrofit() =
            Retrofit.Builder()
                .baseUrl(APIConstants.MOVIES_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        fun <S> createService(serviceClass: Class<S>): S {
            return getInstance()!!.create(serviceClass)
        }
    }
}