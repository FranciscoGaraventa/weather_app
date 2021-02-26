package com.example.weatherapp.data.services.response

import com.google.gson.annotations.SerializedName

data class CloudsResponse(
    @SerializedName("all") val cloudiness: String
)
