package com.example.weatherapp.data.services.builder

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRequestBuilder {

    private val httpClient: OkHttpClient = OkHttpClient.Builder().build()
    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    fun <T> buildService(serviceClass: Class<T>): T =
        builder.client(httpClient).build().create(serviceClass)

}
