package dev.nonoxy.weather_report.models

data class WeatherUI(
    val coordinates: CoordinatesUI,
    val mainInfo: MainInfoUI,
)

data class CoordinatesUI(
    val longitude: Float,
    val latitude: Float,
)

data class MainInfoUI(
    val temperature: Float,
)

data class CityUI(
    val cityName: String,
    val coordinates: CoordinatesUI
)