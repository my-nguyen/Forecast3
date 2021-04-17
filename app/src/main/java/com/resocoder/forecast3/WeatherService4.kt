package com.resocoder.forecast3

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// http://api.weatherstack.com/current?access_key=3ed822f565a1b40e74718a346a592e9e&query=London

interface WeatherService4 {
    @GET("current")
    suspend fun getCurrent(@Query("query") location: String): Weather

    companion object {
        private const val API_KEY = "3ed822f565a1b40e74718a346a592e9e"

        private fun requestInterceptor() = Interceptor { chain ->
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

        private fun client(connectivityInterceptor: ConnectivityInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor())
            .addInterceptor(connectivityInterceptor)
            .build()

        fun retrofit(connectivityInterceptor: ConnectivityInterceptor): Retrofit = Retrofit.Builder()
            .client(client(connectivityInterceptor))
            .baseUrl("http://api.weatherstack.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): WeatherService4 =
            retrofit(connectivityInterceptor).create(WeatherService4::class.java)
    }
}