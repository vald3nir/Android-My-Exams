package com.vald3nir.tolkit.networking.setup

import com.google.gson.GsonBuilder
import com.vald3nir.tolkit.networking.interceptors.ContentTypeInterceptor
import com.vald3nir.tolkit.networking.interceptors.CurlLoggingInterceptor
import com.vald3nir.tolkit.networking.interceptors.loggerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkingSetup {

    fun baseInterceptors() = listOf<Interceptor>(
        loggerInterceptor(),
        ContentTypeInterceptor(),
        CurlLoggingInterceptor()
    )

    fun buildOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        baseInterceptors().forEach { interceptor ->
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    fun buildRetrofit(baseURL: String): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(buildOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    inline fun <reified T> provideApiService(baseURL: String): T {
        return buildRetrofit(baseURL).create(T::class.java)
    }
}