package com.cerminnovations.moviesboard.data.remote.api

import com.cerminnovations.core.constant.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    val apiService by lazy { retrofitService<APIService>() }
}

private fun httpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
    }.build()
}

private fun getMoshi(): Moshi {
    return Moshi.Builder().apply {
        this.add(KotlinJsonAdapterFactory())
    }.build()
}

private fun getRetrofit(): Retrofit {
    return Retrofit.Builder().apply {
        baseUrl(Constants.BASE_URL)
        client(httpClient())
        addConverterFactory(MoshiConverterFactory.create(getMoshi()))
    }.build()
}

private inline fun <reified T> retrofitService(): T = getRetrofit().create(T::class.java)
