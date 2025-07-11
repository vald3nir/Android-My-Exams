package com.vald3nir.tolkit.networking.interceptors

import okhttp3.logging.HttpLoggingInterceptor

fun loggerInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
//            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}