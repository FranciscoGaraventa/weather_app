package com.example.weatherapp.mvp.presenter

import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.weatherapp.mvp.contract.WeatherContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class WeatherPresenter(
    private val model: WeatherContract.Model,
    private val view: WeatherContract.View
) : WeatherContract.Presenter {

    override fun getForecast() {
        view.showProgressBar()
        model.getWeatherData(CITY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                model.saveWeatherData(data)
                view.hideProgressBar()
                view.showData(model.getFilteredForecast(HOUR))
            }, {
                view.hideProgressBar()
                view.showConnectionMessageError()
            })
    }

    override fun onForecastPressed(date: String) {
        view.showForecastDayFragment(model.getFilteredForecast(date))
    }

    companion object {
        private const val CITY = "Tandil"
        private const val HOUR = "00:00"
    }
}
