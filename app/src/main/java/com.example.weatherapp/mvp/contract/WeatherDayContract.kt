package com.example.weatherapp.mvp.contract

import com.example.weatherapp.data.model.ForecastModel

interface WeatherDayContract {

    interface Presenter {
        fun showForecastDay(forecastList: List<ForecastModel>)
    }

    interface View {
        fun showForecastOfDay(forecastList: List<ForecastModel>)
    }
}
