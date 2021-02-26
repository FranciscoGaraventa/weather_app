package com.example.weatherapp.data.services.response

import com.google.gson.annotations.SerializedName

data class TemperatureResponse(
    @SerializedName("temp") val temperature: String,
    @SerializedName("feels_like") val thermalSensation: String,
    @SerializedName("temp_min") val minimumTemperature: String,
    @SerializedName("temp_max") val maximumTemperature: String,
    @SerializedName("pressure") val atmosphericPressure: String,
    @SerializedName("sea_level") val atmosphericSeaLevel: String,
    @SerializedName("gnrd_level") val atmosphericGroundLevel: String,
    @SerializedName("humidity") val humidity: String,
    @SerializedName("temp_kf") val tempKf: String
)
