package com.resocoder.forecast3

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// http://api.weatherstack.com/current?access_key=3ed822f565a1b40e74718a346a592e9e&query=London

interface WeatherService {
    companion object {
        private const val API_KEY = "3ed822f565a1b40e74718a346a592e9e"

        private fun interceptor() = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("access_key", API_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        private fun client(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor())
            .build()

        fun retrofit(): Retrofit = Retrofit.Builder()
            .client(client())
            .baseUrl("http://api.weatherstack.com/")
            // .addCallAdapterFactory(CoroutineCallAdapterFactory()) // not needed since getCurrent() doesn't return a Deferred<Weather>
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}