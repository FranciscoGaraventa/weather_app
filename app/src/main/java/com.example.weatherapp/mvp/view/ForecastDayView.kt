package com.example.weatherapp.mvp.view

import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.WeatherAdapter
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.databinding.WeatherDialogBinding
import com.example.weatherapp.mvp.contract.WeatherDayContract
import com.example.weatherapp.mvp.view.base.FragmentView

class ForecastDayView(fragment: DialogFragment, private val binding: WeatherDialogBinding) :
    FragmentView(fragment), WeatherDayContract.View {

    override fun showForecastOfDay(forecastList: List<ForecastModel>) {
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.weatherRecyclerView.adapter = WeatherAdapter(forecastList)
    }
}
