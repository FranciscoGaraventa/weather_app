package com.example.weatherapp.mvp.view

import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.WeatherAdapter
import com.example.weatherapp.activity.WeatherActivity
import com.example.weatherapp.data.dialog.ForecastDayFragment
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.databinding.WeatherMainBinding
import com.example.weatherapp.mvp.contract.WeatherContract
import com.example.weatherapp.mvp.view.base.ActivityView

class WeatherView(activity: WeatherActivity, private val binding: WeatherMainBinding) :
    ActivityView(activity), WeatherContract.View {

    override fun showData(data: List<ForecastModel>) {
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.weatherRecyclerView.adapter = WeatherAdapter(data) { item ->
            (activity as WeatherActivity).onCardPressed(item.dateAndTime)
        }
    }

    override fun showForecastDayFragment(forecastList: List<ForecastModel>) {
        ForecastDayFragment.newInstance(ArrayList(forecastList))
            .show((context as FragmentActivity).supportFragmentManager, ForecastDayFragment.TAG)
    }

    override fun showProgressBar() {
        binding.mainProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.mainProgressBar.visibility = View.GONE
    }

    override fun showConnectionMessageError() {
        Toast.makeText(context, context?.getString(R.string.connection_message_error), Toast.LENGTH_SHORT).show()
    }
}
