package com.example.weatherapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.mvp.view.WeatherView
import com.example.weatherapp.mvp.model.WeatherModel
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.mvp.contract.WeatherContract
import com.example.weatherapp.data.services.WeatherService
import com.example.weatherapp.mvp.presenter.WeatherPresenter
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.data.services.builder.WeatherRequestBuilder

class WeatherActivity : AppCompatActivity() {

    private lateinit var presenter: WeatherContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        presenter = WeatherPresenter(
            WeatherModel(
                WeatherService(
                    WeatherRequestBuilder(),
                    WeatherMapper
                )
            ), WeatherView(this, binding)
        )
        presenter.getForecast()
    }

}
