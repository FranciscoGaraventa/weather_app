package com.example.weatherapp.mvp.contract

import com.example.weatherapp.data.model.ForecastModel
import io.reactivex.rxjava3.core.Observable

interface WeatherContract {

    interface Presenter{
        fun getForecast()
    }

    interface View{

    }

    interface Model{
        fun getWeatherData(city: String): Observable<MutableList<ForecastModel>>
    }
}
