package com.example.weatherapp.mvp.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.WeatherAdapter
import com.example.weatherapp.activity.WeatherActivity
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.databinding.WeatherMainBinding
import com.example.weatherapp.mvp.contract.WeatherContract
import com.example.weatherapp.mvp.view.base.ActivityView

class WeatherView(activity: WeatherActivity, private val binding: WeatherMainBinding): ActivityView(activity), WeatherContract.View {

    override fun showData(data: List<ForecastModel>){
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.weatherRecyclerView.adapter = WeatherAdapter(data)
    }
}
