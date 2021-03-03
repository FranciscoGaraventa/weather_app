package com.example.weatherapp.mvpTest

import com.example.weatherapp.Utils.Constants.ATMOSPHERIC_GROUND_LEVEL
import com.example.weatherapp.Utils.Constants.ATMOSPHERIC_PRESSURE
import com.example.weatherapp.Utils.Constants.ATMOSPHERIC_SEA_LEVEL
import com.example.weatherapp.Utils.Constants.CLOUDINESS
import com.example.weatherapp.Utils.Constants.DATE_AND_TIME_THREE
import com.example.weatherapp.Utils.Constants.HUMIDITY
import com.example.weatherapp.Utils.Constants.MAXIMUM_TEMPERATURE
import com.example.weatherapp.Utils.Constants.MINIMUM_TEMPERATURE
import com.example.weatherapp.Utils.Constants.PART_OF_DAY
import com.example.weatherapp.Utils.Constants.PROBABILITY_OF_PRECIPITATION
import com.example.weatherapp.Utils.Constants.TEMPERATURE
import com.example.weatherapp.Utils.Constants.TEMP_KF
import com.example.weatherapp.Utils.Constants.THERMAL_SENSATION
import com.example.weatherapp.Utils.Constants.TIME_DATA_FORECAST
import com.example.weatherapp.Utils.Constants.VISIBILITY
import com.example.weatherapp.Utils.Constants.WEATHER_CONDITION_DESCRIPTION
import com.example.weatherapp.Utils.Constants.WEATHER_CONDITION_ID
import com.example.weatherapp.Utils.Constants.WEATHER_ICON_ID
import com.example.weatherapp.Utils.Constants.WEATHER_TYPE
import com.example.weatherapp.Utils.Constants.WIND_DIRECTION_DEGREES
import com.example.weatherapp.Utils.Constants.WIND_SPEED
import com.example.weatherapp.data.model.CloudsDescription
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.model.PartOfDayDescription
import com.example.weatherapp.data.model.TemperatureDescription
import com.example.weatherapp.data.model.WeatherDescription
import com.example.weatherapp.data.model.WindDescription
import com.example.weatherapp.mvp.contract.WeatherDayContract
import com.example.weatherapp.mvp.presenter.WeatherDayPresenter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class WeatherDayPresenterUnitTest {

    private var view: WeatherDayContract.View = mock()
    private lateinit var presenter: WeatherDayContract.Presenter
    private lateinit var forecastData: List<ForecastModel>

    companion object {

        private fun createForecastModel(dateAndTime: String): ForecastModel {
            val weatherList: MutableList<WeatherDescription> = mutableListOf()
            weatherList.add(
                WeatherDescription(
                    WEATHER_CONDITION_ID,
                    WEATHER_TYPE,
                    WEATHER_CONDITION_DESCRIPTION,
                    WEATHER_ICON_ID
                )
            )
            return ForecastModel(
                TIME_DATA_FORECAST,
                TemperatureDescription(
                    TEMPERATURE,
                    THERMAL_SENSATION,
                    MINIMUM_TEMPERATURE,
                    MAXIMUM_TEMPERATURE,
                    ATMOSPHERIC_PRESSURE,
                    ATMOSPHERIC_SEA_LEVEL,
                    ATMOSPHERIC_GROUND_LEVEL,
                    HUMIDITY,
                    TEMP_KF
                ),
                weatherList,
                CloudsDescription(
                    CLOUDINESS
                ),
                WindDescription(
                    WIND_SPEED,
                    WIND_DIRECTION_DEGREES
                ),
                VISIBILITY,
                PROBABILITY_OF_PRECIPITATION,
                PartOfDayDescription(
                    PART_OF_DAY
                ),
                dateAndTime
            )
        }

        private fun createExpectedListData(): List<ForecastModel> {
            return listOf(
                createForecastModel(DATE_AND_TIME_THREE)
            )
        }
    }

    @Before
    fun setup() {
        presenter = WeatherDayPresenter(view)
        forecastData = createExpectedListData()
    }

    @Test
    fun `showing data on fragment dialog of extended forecast date`() {
        presenter.showForecastDay(forecastData)
        verify(view).showForecastOfDay(forecastData)
    }
}
