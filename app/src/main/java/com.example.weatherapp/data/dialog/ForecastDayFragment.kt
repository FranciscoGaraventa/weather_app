package com.example.weatherapp.data.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.databinding.WeatherDialogBinding
import com.example.weatherapp.mvp.contract.WeatherDayContract
import com.example.weatherapp.mvp.presenter.WeatherDayPresenter
import com.example.weatherapp.mvp.view.ForecastDayView

class ForecastDayFragment : DialogFragment() {

    private lateinit var presenter: WeatherDayContract.Presenter
    private lateinit var binding: WeatherDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = WeatherDialogBinding.inflate(inflater, container, false)
        presenter = WeatherDayPresenter(ForecastDayView(this, binding))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val forecastDay: ArrayList<ForecastModel>? =
            arguments?.getSerializable(FORECAST_BY_DAY) as? ArrayList<ForecastModel>
        forecastDay?.let {
            presenter.showForecastDay(forecastDay)
        }
    }

    companion object {
        private const val FORECAST_BY_DAY = "FORECAST_BY_DAY"
        const val TAG = "WEATHER_FRAGMENT_TAG"

        fun newInstance(forecastOfDay: ArrayList<ForecastModel>): ForecastDayFragment {
            val args = Bundle()
            args.apply {
                putSerializable(FORECAST_BY_DAY, forecastOfDay)
            }
            val fragment = ForecastDayFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
