package com.example.weatherapp.mvp.model

import io.reactivex.rxjava3.core.Observable
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.services.WeatherService
import com.example.weatherapp.mvp.contract.WeatherContract

class WeatherModel(private val weatherService: WeatherService) : WeatherContract.Model {

    override fun getWeatherData(city: String): Observable<MutableList<ForecastModel>> =
        weatherService.getForecast(city)
}
