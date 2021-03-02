package com.example.weatherapp.mvp.presenter

import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.mvp.contract.WeatherDayContract

class WeatherDayPresenter(private val view: WeatherDayContract.View) :
    WeatherDayContract.Presenter {

    override fun showForecastDay(forecastList: List<ForecastModel>) {
        view.showForecastOfDay(forecastList)
    }
}
