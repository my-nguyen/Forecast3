package com.resocoder.forecast3

import androidx.room.TypeConverter
import com.google.gson.Gson

// this singleton class is necessary to convert between a List<String> and a database column for
// fields weather_icons and weather_descriptions of data class Current so the class can be stored
// as a table in the database
object Converters {
    @TypeConverter
    @JvmStatic // to avoid compiler error: Converters() has private access in Converters
    fun fromList(value: List<String>) = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun fromString(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}