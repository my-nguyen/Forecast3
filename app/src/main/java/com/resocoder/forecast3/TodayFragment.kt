package com.resocoder.forecast3

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.resocoder.forecast3.databinding.FragmentTodayBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayFragment : Fragment(R.layout.fragment_today) {

    private lateinit var binding: FragmentTodayBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTodayBinding.bind(view)

        // serviceWithoutCoroutine()
        serviceWithCoroutine()
    }

    private fun serviceWithCoroutine2() {
        val interceptor = ConnectivityInterceptor(requireContext())
        val service = WeatherService4(interceptor)
        val dataSource = NetworkDataSourceImpl(service)
        dataSource.currentWeather.observe(this, {
            binding.today.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            dataSource.getCurrent("San Jose, CA")
        }
    }

    private fun serviceWithCoroutine() {
        CoroutineScope(Dispatchers.IO).launch {
            val weather = WeatherService3().getCurrent("San Jose, CA")
            withContext(Dispatchers.Main) {
                binding.today.text = weather.current.toString()
            }
        }
    }

    private fun serviceWithoutCoroutine() {
        // val weather = retrofitResoCoder()
        val weather = retrofitNormally()
        weather.enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if (body == null) {
                    Log.w(TAG, "Did not receive valid response body from Yelp API")
                } else {
                    binding.today.text = body.current.toString()
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }
        })
    }

    private fun retrofitResoCoder() = WeatherService2().getCurrent("London")

    private fun retrofitNormally(): Call<Weather> {
        val service = WeatherService1.retrofit().create(WeatherService1::class.java)
        return service.getCurrent("3ed822f565a1b40e74718a346a592e9e", "London")
    }

    companion object {
        const val TAG = "TodayFragment"
    }
}