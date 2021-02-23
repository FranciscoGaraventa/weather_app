package com.example.weatherapp.mvp.presenter

import com.example.weatherapp.mvp.contract.WeatherContract

class WeatherPresenter(private val model: WeatherContract.Model, private val view: WeatherContract.View): WeatherContract.Presenter {
}
