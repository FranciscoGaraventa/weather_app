package com.example.weatherapp.data.model

data class TemperatureDescription(
    val temperature: String,
    val thermalSensation: String,
    val minimumTemperature: String,
    val maximumTemperature: String,
    val atmosphericPressure: String,
    val atmosphericSeaLevel: String,
    val atmosphericGroundLevel: String?,
    val humidity: String,
    val tempKf: String,
)
