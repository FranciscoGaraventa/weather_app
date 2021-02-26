package com.example.weatherapp.data.services.response

import com.google.gson.annotations.SerializedName

data class ModelResponse(
    @SerializedName("dt") val timeDataForecast: String,
    @SerializedName("main") val temperature: TemperatureResponse,
    @SerializedName("weather") val currentWeather: MutableList<CurrentWeatherResponse>,
    @SerializedName("clouds") val clouds: CloudsResponse,
    @SerializedName("wind") val wind: WindResponse,
    @SerializedName("visibility") val visibility: String,
    @SerializedName("pop") val probabilityOfPrecipitation: String,
    @SerializedName("sys") val partOfTheDay: PartOfDayResponse,
    @SerializedName("dt_txt") val dateAndTime: String
)
