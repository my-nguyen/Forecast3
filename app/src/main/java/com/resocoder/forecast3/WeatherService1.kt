package com.resocoder.forecast3

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// http://api.weatherstack.com/current?access_key=3ed822f565a1b40e74718a346a592e9e&query=London

interface WeatherService1 {
    @GET("current")
    fun getCurrent(@Query("access_key") apiKey: String, @Query("query") location: String): Call<Weather>

    @GET("current")
    fun getCurrent(@Query("query") location: String): Call<Weather>

    companion object {
        const val API_KEY = "3ed822f565a1b40e74718a346a592e9e"

        operator fun invoke(): WeatherService1 {
            val interceptor = Interceptor { chain ->
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

            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            return Retrofit.Builder()
                    .client(client)
                    .baseUrl("http://api.weatherstack.com/")
                    // .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(WeatherService1::class.java)
        }
    }
}