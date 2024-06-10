package dev.nonoxy.weather_report.ui.models

import dev.nonoxy.weather_report.models.CityUI
import dev.nonoxy.weather_report.models.CoordinatesUI

sealed class WeatherReportViewState {
    data object Loading : WeatherReportViewState()
    data class Error(val city: CityUI?) : WeatherReportViewState()
    data class Display(
        val temperature: Byte,
        val cityName: String,
        val coordinates: CoordinatesUI,
    ) : WeatherReportViewState()
}