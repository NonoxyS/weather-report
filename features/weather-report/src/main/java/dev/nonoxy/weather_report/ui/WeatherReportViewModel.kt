package dev.nonoxy.weather_report.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nonoxy.weather.data.RequestResult
import dev.nonoxy.weather.theme.base.BaseViewModel
import dev.nonoxy.weather_report.GetWeatherReportUseCase
import dev.nonoxy.weather_report.models.CityUI
import dev.nonoxy.weather_report.models.WeatherUI
import dev.nonoxy.weather_report.ui.models.WeatherReportAction
import dev.nonoxy.weather_report.ui.models.WeatherReportEvent
import dev.nonoxy.weather_report.ui.models.WeatherReportViewState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class WeatherReportViewModel @Inject constructor(
    private val getWeatherReportUseCase: GetWeatherReportUseCase
) : BaseViewModel<WeatherReportViewState, WeatherReportAction, WeatherReportEvent>(initialState = WeatherReportViewState.Loading) {

    override fun obtainEvent(viewEvent: WeatherReportEvent) {
        when (viewEvent) {
            is WeatherReportEvent.LoadData -> fetchData(viewEvent.city)
        }
    }

    private fun fetchData(city: CityUI) {
        viewModelScope.launch {
            getWeatherReportUseCase.execute(
                latitude = city.coordinates.latitude.toString(),
                longitude = city.coordinates.longitude.toString()
            )
                .map { it.toWeatherReportViewState(city = city) }
                .collect { newState ->
                    viewState = newState
                }
        }
    }

    private fun RequestResult<WeatherUI>.toWeatherReportViewState(city: CityUI): WeatherReportViewState {
        return when (this) {
            is RequestResult.Error -> WeatherReportViewState.Error(city = city)
            is RequestResult.InProgress -> WeatherReportViewState.Loading
            is RequestResult.Success -> {
                WeatherReportViewState.Display(
                    temperature = data.mainInfo.temperature.roundToInt().toByte(),
                    cityName = city.cityName,
                    coordinates = data.coordinates
                )
            }
        }
    }
}