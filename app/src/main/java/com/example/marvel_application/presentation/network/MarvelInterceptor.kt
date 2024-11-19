package com.example.marvel_application.presentation.network

import okhttp3.Interceptor
import okhttp3.Response

class MarvelInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val url = originalUrl.newBuilder()
            .addQueryParameter("ts", MarvelConfig.ts.toString())
            .addQueryParameter("apikey", MarvelConfig.PUBLIC_KEY)
            .addQueryParameter("hash", MarvelConfig.getHash())
            .build()

        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}