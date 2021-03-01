package com.example.weatherapp.mvp.contract

import com.example.weatherapp.data.model.ForecastModel
import io.reactivex.rxjava3.core.Observable

interface WeatherContract {

    interface Presenter {
        fun getForecast()
        fun onForecastPressed(date: String)
    }

    interface View {
        fun showData(data: List<ForecastModel>)
        fun showForecastDayFragment(forecastList: List<ForecastModel>)
    }

    interface Model {
        fun getWeatherData(city: String): Observable<MutableList<ForecastModel>>
        fun getFilteredForecast(date: String): List<ForecastModel>
        fun saveWeatherData(forecastList: List<ForecastModel>)
    }
}
