package com.resocoder.forecast3

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.resocoder.forecast3.databinding.FragmentTodayBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TodayFragment : Fragment(R.layout.fragment_today) {

    private lateinit var binding: FragmentTodayBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTodayBinding.bind(view)

        val weather = retrofit1()
        // val weather = retrofit2()
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

    private fun retrofit1() = WeatherService1().getCurrent("London")

    /*private fun retrofit2(): Call<Weather> {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.weatherstack.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(WeatherService1:class.java)
        return service.getCurrent("3ed822f565a1b40e74718a346a592e9e","London")
    }*/

    companion object {
        const val TAG = "TodayFragment"
    }
}