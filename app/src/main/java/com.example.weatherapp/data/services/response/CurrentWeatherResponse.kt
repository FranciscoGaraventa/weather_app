package com.example.weatherapp.data.services.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("id") val weatherConditionId: String,
    @SerializedName("main") val weatherType: String,
    @SerializedName("description") val weatherConditionDescription: String,
    @SerializedName("icon") val weatherIconId: String
)
