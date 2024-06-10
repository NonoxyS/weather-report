package dev.nonoxy.weather.cities.models

data class CityUI(
    val id: String,
    val cityName: String,
    val coordinates: CoordinatesUI
)

data class CoordinatesUI(
    val latitude: Float,
    val longitude: Float
)
