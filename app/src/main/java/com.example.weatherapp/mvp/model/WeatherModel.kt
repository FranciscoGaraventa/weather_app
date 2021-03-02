package com.example.weatherapp.mvp.model

import io.reactivex.rxjava3.core.Observable
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.services.WeatherService
import com.example.weatherapp.mvp.contract.WeatherContract

class WeatherModel(private val weatherService: WeatherService) : WeatherContract.Model {

    private lateinit var forecastList: List<ForecastModel>

    override fun getWeatherData(city: String): Observable<MutableList<ForecastModel>> =
        weatherService.getForecast(city)

    override fun saveWeatherData(forecastList: List<ForecastModel>) {
        this.forecastList = forecastList
    }

    override fun getFilteredForecast(date: String): List<ForecastModel> {
        return forecastList.filter { it.dateAndTime.contains(date) }
    }
}
