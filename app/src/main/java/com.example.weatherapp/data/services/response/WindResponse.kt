package com.example.weatherapp.data.services.response

import com.google.gson.annotations.SerializedName

data class WindResponse(
    @SerializedName("speed") val windSpeed: String,
    @SerializedName("deg") val windDirectionDegrees: String
)
