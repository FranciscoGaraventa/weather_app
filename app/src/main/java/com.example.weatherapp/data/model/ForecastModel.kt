package com.example.weatherapp.data.model

data class ForecastModel(
    val timeDataForecast: String,
    val temperature: TemperatureDescription,
    val currentWeather: MutableList<WeatherDescription>,
    val clouds: CloudsDescription,
    val wind: WindDescription,
    val visibility: String,
    val probabilityOfPrecipitation: String,
    val partOfTheDay: PartOfDayDescription,
    val dateAndTime: String
)
