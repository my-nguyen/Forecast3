package com.resocoder.forecast3

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService3: WeatherService {
    @GET("current")
    suspend fun getCurrent(@Query("query") location: String): Weather

    companion object {
        operator fun invoke(): WeatherService3 = WeatherService.retrofit().create(WeatherService3::class.java)
    }
}