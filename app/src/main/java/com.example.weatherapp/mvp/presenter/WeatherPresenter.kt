package com.example.weatherapp.mvp.presenter

import android.util.Log
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.weatherapp.mvp.contract.WeatherContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class WeatherPresenter(
    private val model: WeatherContract.Model,
    private val view: WeatherContract.View
) : WeatherContract.Presenter {

    override fun getForecast() {
        model.getWeatherData(CITY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data -> Log.i("DATA", data.toString()) }
    }

    companion object {
        private const val CITY = "Tandil"
    }
}
