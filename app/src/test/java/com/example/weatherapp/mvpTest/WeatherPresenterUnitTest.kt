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
import com.example.weatherapp.Utils.WeatherTestUtils.immediate
import com.example.weatherapp.data.model.CloudsDescription
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.model.PartOfDayDescription
import com.example.weatherapp.data.model.TemperatureDescription
import com.example.weatherapp.data.model.WeatherDescription
import com.example.weatherapp.data.model.WindDescription
import com.example.weatherapp.data.services.WeatherService
import com.example.weatherapp.mvp.contract.WeatherContract
import com.example.weatherapp.mvp.model.WeatherModel
import com.example.weatherapp.mvp.presenter.WeatherPresenter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class WeatherPresenterUnitTest {

    private var serviceMock: WeatherService = mock()
    private var modelMock = WeatherModel(serviceMock)
    private var view: WeatherContract.View = mock()
    private lateinit var presenter: WeatherContract.Presenter
    private lateinit var forecastData: MutableList<ForecastModel>
    private lateinit var expectedData: List<ForecastModel>

    companion object {

        private const val CITY = "Tandil"
        private const val DATE = "05/03"
        private const val DATE_AND_TIME_ONE = "Wed, 03/03 06:00"
        private const val DATE_AND_TIME_TWO = "Thu, 04/03 03:00"

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

        private fun createMutableListTestData(): MutableList<ForecastModel> {
            return mutableListOf(
                createForecastModel(DATE_AND_TIME_ONE),
                createForecastModel(DATE_AND_TIME_TWO),
                createForecastModel(DATE_AND_TIME_THREE)
            )
        }

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            RxJavaPlugins.setInitIoSchedulerHandler { immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
        }
    }

    @Before
    fun setup() {
        presenter = WeatherPresenter(modelMock, view)
        forecastData = createMutableListTestData()
        expectedData = createExpectedListData()
    }

    @Test
    fun `checking success call on getForecast `() {
        whenever(serviceMock.getForecast(CITY)).thenReturn(Observable.just(forecastData))
        presenter.getForecast()
        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showData(expectedData)
        verifyZeroInteractions(view)
        Assert.assertEquals(expectedData, modelMock.getFilteredForecast(DATE))
    }

    @Test
    fun `checking throwable error on getForecast call`() {
        whenever(serviceMock.getForecast(CITY)).thenReturn(Observable.error(Throwable()))
        presenter.getForecast()
        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showConnectionMessageError()
    }

    @Test
    fun `checking on card pressed`() {
        modelMock.saveWeatherData(forecastData)
        presenter.onForecastPressed(DATE)
        verify(view).showForecastDayFragment(expectedData)
        Assert.assertEquals(expectedData, modelMock.getFilteredForecast(DATE))
    }
}
