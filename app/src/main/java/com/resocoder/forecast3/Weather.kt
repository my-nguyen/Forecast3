package com.resocoder.forecast3

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_ID = 0

// @Entity(tableName = "weather")
data class Weather(/*@Embedded(prefix = "location_")*/ val location: Location,
                   /*@Embedded(prefix = "current_")*/ val current: Current)

data class Location(val name: String, val country: String, val region: String, val lat: Float,
                    val Lon: Float, val timezone_id: String, val localtime: String, val localtime_epoch: Int)

@Entity(tableName = "current")
data class Current(val observation_time: String, val temperature: Int, val weather_code: Int,
                   val weather_icons: List<String>, val weather_descriptions: List<String>,
                   val wind_speed: Int, val wind_dir: String, val precip: Int, val feelslike: Int,
                   val uv_index: Int, val visibility: Int, val is_day: String) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_ID
}
