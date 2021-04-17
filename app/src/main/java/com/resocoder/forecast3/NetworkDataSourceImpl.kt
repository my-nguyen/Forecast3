package com.resocoder.forecast3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkDataSourceImpl(private val service: WeatherService4) : NetworkDataSource {
    private val _currentWeather = MutableLiveData<Weather>()
    override val currentWeather: LiveData<Weather>
        get() = _currentWeather

    override suspend fun getCurrent(location: String) {
        try {
            val weather = service.getCurrent(location)
                // .await()
            _currentWeather.postValue(weather)
        } catch (e: NoConnectivityException) {
            Log.e("NetworkDataSourceImpl", "No internet connection", e)
        }
    }
}