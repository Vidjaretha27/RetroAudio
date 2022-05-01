package com.dicoding.retroaudio

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Api {
    @Multipart
    @POST("api/predict-bilah")
    fun uploadFile(
            @Part audio: MultipartBody.Part
    ) : Call <Result>

}
