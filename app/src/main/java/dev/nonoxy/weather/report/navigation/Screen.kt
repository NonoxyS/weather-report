package dev.nonoxy.weather.report.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object Cities

    @Serializable
    data class WeatherReport(
        val cityName: String,
        val latitude: Float,
        val longitude: Float
    )
}