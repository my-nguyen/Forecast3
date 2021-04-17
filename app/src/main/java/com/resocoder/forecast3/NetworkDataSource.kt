package com.resocoder.forecast3

import androidx.lifecycle.LiveData

interface NetworkDataSource {
    val currentWeather: LiveData<Weather>

    suspend fun getCurrent(location: String)
}