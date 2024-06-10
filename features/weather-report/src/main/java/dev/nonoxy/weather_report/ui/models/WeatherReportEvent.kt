package dev.nonoxy.weather_report.ui.models

import dev.nonoxy.weather_report.models.CityUI

sealed class WeatherReportEvent {
    data class LoadData(val city: CityUI) : WeatherReportEvent()
}