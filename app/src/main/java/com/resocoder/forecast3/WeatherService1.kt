package com.resocoder.forecast3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService1: WeatherService {
    @GET("current")
    fun getCurrent(@Query("access_key") apiKey: String, @Query("query") location: String): Call<Weather>

    @GET("current")
    fun getCurrent(@Query("query") location: String): Call<Weather>

    companion object {
        operator fun invoke(): WeatherService1 = WeatherService.retrofit().create(WeatherService1::class.java)
    }
}