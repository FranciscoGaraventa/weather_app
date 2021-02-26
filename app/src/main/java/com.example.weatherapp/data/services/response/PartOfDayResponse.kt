package com.example.weatherapp.data.services.response

import com.google.gson.annotations.SerializedName

data class PartOfDayResponse(
    @SerializedName("pod") val probabilityOfPrecipitation: String
)
