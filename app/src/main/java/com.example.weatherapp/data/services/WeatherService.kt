package com.example.weatherapp.data.services

import com.example.weatherapp.BuildConfig
import io.reactivex.rxjava3.core.Observable
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.data.services.api.WeatherApiProvider
import com.example.weatherapp.data.services.builder.WeatherRequestBuilder

class WeatherService(private val api: WeatherRequestBuilder, private val mapper: WeatherMapper) {

    companion object {
        private const val METRIC = "metric"
    }

    fun getForecast(city: String): Observable<MutableList<ForecastModel>> {
        return Observable.create { subscriber ->
            val callResponse =
                api.buildService(WeatherApiProvider::class.java)
                    .getWeather(city, BuildConfig.APP_ID, METRIC)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.list?.let { subscriber.onNext(mapper.transformList(it)) }
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}
