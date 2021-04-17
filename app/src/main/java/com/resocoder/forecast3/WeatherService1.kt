package com.resocoder.forecast3

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// http://api.weatherstack.com/current?access_key=3ed822f565a1b40e74718a346a592e9e&query=London

interface WeatherService1 {
    @GET("current")
    fun getCurrent(@Query("access_key") apiKey: String, @Query("query") location: String): Call<Weather>

    companion object {
        fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherstack.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}