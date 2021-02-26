package com.example.weatherapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.databinding.WeatherCardItemBinding
import com.example.weatherapp.utils.inflate
import com.example.weatherapp.utils.loadUrl

class WeatherAdapter(private val days: List<ForecastModel>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherViewHolder(parent.inflate(R.layout.weather_card_item, false))


    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(days.elementAt(position))
    }

    override fun getItemCount(): Int = days.size

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = WeatherCardItemBinding.bind(itemView)
        fun bind(item: ForecastModel) {
            binding.apply {
                binding.weatherImageView.loadUrl(
                    StringBuilder().append(URL)
                        .append(item.currentWeather.first().weatherIconId)
                        .append(FORMAT)
                        .toString(),
                    RequestOptions().placeholder(R.drawable.default_image)
                )
                weatherDescription.text =
                    item.currentWeather.first().weatherConditionDescription.toUpperCase()
                weatherForecastDate.text = item.dateAndTime

                weatherMinimumTemperature.text = itemView.resources.getString(
                    R.string.temperature_unit,
                    itemView.resources.getString(
                        R.string.minimum_temperature_card,
                        item.temperature.minimumTemperature
                    )
                )

                weatherMaximumTemperature.text = itemView.resources.getString(
                    R.string.temperature_unit,
                    itemView.resources.getString(
                        R.string.maximum_temperature_card,
                        item.temperature.maximumTemperature
                    )
                )

                weatherThermalSensation.text = itemView.resources.getString(
                    R.string.temperature_unit,
                    itemView.resources.getString(
                        R.string.thermal_sensation_card,
                        item.temperature.thermalSensation
                    )
                )
            }

        }
    }

    companion object {
        private const val URL = "https://openweathermap.org/img/wn/"
        private const val FORMAT = ".png"
    }
}
