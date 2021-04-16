package com.resocoder.forecast3

data class Weather(val location: Location, val current: Current)

data class Location(val name: String, val country: String, val region: String, val lat: Float,
                    val Lon: Float, val timezone_id: String, val localtime: String, val localtime_epoch: Int)

data class Current(val observation_time: String, val temperature: Int, val weather_code: Int,
                   val weather_icons: List<String>, val weather_descriptions: List<String>,
                   val wind_speed: Int, val wind_degree: Int, val wind_dir: String,
                   val pressure: Int, val precip: Int, val humidity: Int, val cloudcover: Int,
                   val feelslike: Int, val uv_index: Int, val visibility: Int, val is_day: String)
