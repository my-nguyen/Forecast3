package com.resocoder.forecast3

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService2: WeatherService {
    @GET("current")
    suspend fun getCurrent(@Query("access_key") apiKey: String, @Query("query") location: String): Weather

    @GET("current")
    suspend fun getCurrent(@Query("query") location: String): Weather

    companion object {
        operator fun invoke() = WeatherService.retrofit().create(WeatherService2::class.java)
    }
}