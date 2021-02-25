package com.example.weatherapp.data.mapper

import java.util.Date
import java.util.Locale
import java.text.SimpleDateFormat
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.model.PartOfDayDescription
import com.example.weatherapp.data.model.WindDescription
import com.example.weatherapp.utils.Constants.API_PATTERN
import com.example.weatherapp.utils.Constants.APP_PATTERN
import com.example.weatherapp.data.model.CloudsDescription
import com.example.weatherapp.data.model.WeatherDescription
import com.example.weatherapp.data.model.TemperatureDescription
import com.example.weatherapp.data.services.response.PartOfDayResponse
import com.example.weatherapp.data.services.response.WindResponse
import com.example.weatherapp.data.services.response.ModelResponse
import com.example.weatherapp.data.services.response.CloudsResponse
import com.example.weatherapp.data.services.response.TemperatureResponse
import com.example.weatherapp.data.services.response.CurrentWeatherResponse

object WeatherMapper {

    private fun transformMain(temperature: TemperatureResponse) = TemperatureDescription(
        temperature.temperature,
        temperature.thermalSensation,
        temperature.minimumTemperature,
        temperature.maximumTemperature,
        temperature.atmosphericPressure,
        temperature.atmosphericSeaLevel,
        temperature.atmosphericGroundLevel,
        temperature.humidity,
        temperature.tempKf
    )

    private fun transformWeather(currentWeather: MutableList<CurrentWeatherResponse>): MutableList<WeatherDescription> {
        val weatherList: MutableList<WeatherDescription> = mutableListOf()
        currentWeather.forEach {
            weatherList.add(
                WeatherDescription(
                    it.weatherConditionId,
                    it.weatherType,
                    it.weatherConditionDescription,
                    it.weatherIconId
                )
            )
        }
        return weatherList
    }

    private val apiFormat = SimpleDateFormat(API_PATTERN, Locale.ENGLISH)
    private val appFormat = SimpleDateFormat(APP_PATTERN, Locale.ENGLISH)

    private fun transformCloud(clouds: CloudsResponse) = CloudsDescription(clouds.cloudiness)

    private fun transformWind(wind: WindResponse) =
        WindDescription(wind.windSpeed, wind.windDirectionDegrees)

    private fun transformSys(partOfDay: PartOfDayResponse) = PartOfDayDescription(partOfDay.probabilityOfPrecipitation)

    fun transformList(list: List<ModelResponse>): MutableList<ForecastModel> {
        val weatherModelList: MutableList<ForecastModel> = mutableListOf()
        list.forEach {
            weatherModelList.add(
                ForecastModel(
                    it.timeDataForecast,
                    transformMain(it.temperature),
                    transformWeather(it.currentWeather),
                    transformCloud(it.clouds),
                    transformWind(it.wind),
                    it.visibility,
                    it.probabilityOfPrecipitation,
                    transformSys(it.partOfTheDay),
                    appFormat.format(apiFormat.parse(it.dateAndTime) ?: Date())
                )
            )
        }
        return weatherModelList
    }
}
