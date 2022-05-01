package com.dicoding.retroaudio

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    val Url = "https://rindikapp-retha-demo.herokuapp.com/"

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
                .baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}