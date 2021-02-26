package com.example.weatherapp.mvp.view

import com.example.weatherapp.activity.WeatherActivity
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.mvp.contract.WeatherContract
import com.example.weatherapp.mvp.view.base.ActivityView

class WeatherView(activity: WeatherActivity, private val binding: ActivityMainBinding): ActivityView(activity), WeatherContract.View {
}
