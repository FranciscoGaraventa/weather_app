package com.example.weatherapp.mvp.view

import android.app.Activity
import com.example.weatherapp.mvp.contract.WeatherContract
import com.example.weatherapp.mvp.view.base.ActivityView

class WeatherView(activity: Activity): ActivityView(activity), WeatherContract.View {
}
