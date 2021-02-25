package com.example.weatherapp.data.services.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weatherapp.data.services.response.ForecastResponse

interface WeatherApiProvider {

    @GET("forecast")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Call<ForecastResponse>
}
