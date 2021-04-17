package com.resocoder.forecast3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService2: WeatherService {
    @GET("current")
    fun getCurrent(@Query("query") location: String): Call<Weather>

    companion object {
        operator fun invoke() = WeatherService.retrofit().create(WeatherService2::class.java)
    }
}