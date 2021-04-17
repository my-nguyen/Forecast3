package com.resocoder.forecast3

import androidx.room.ColumnInfo

interface CurrentUnit {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}

// Reso Coder's tutorial was made in October 2018, when the JSON returned from api.weatherstack.com
// distinguished between imperial and metric units. this is no longer the case, but the classes
// WeatherCurrentUnit and ImperialCurrentUnit are here to illustrate how to derive from an interface
// and override its member variables.
data class CurrentImperial(
    @ColumnInfo(name = "temperature")
    override val temperature: Double,
    @ColumnInfo(name = "weather_descriptions")
    override val conditionText: String,
    @ColumnInfo(name = "weather_icons")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "wind_speed")
    override val windSpeed: Double,
    @ColumnInfo(name = "wind_dir")
    override val windDirection: String,
    @ColumnInfo(name = "precip")
    override val precipitationVolume: Double,
    @ColumnInfo(name = "feelslike")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name = "visibility")
    override val visibilityDistance: Double
): CurrentUnit

typealias CurrentMetric = CurrentImperial