package com.example.weatherapp.data.services.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("list") val list: MutableList<ModelResponse>
)
