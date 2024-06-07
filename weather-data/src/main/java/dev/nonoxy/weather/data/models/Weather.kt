package dev.nonoxy.weather.data.models

data class Weather(
    val coordinates: Coordinates,
    val mainInfo: MainInfo,
)


data class Coordinates(
    val longitude: Float,
    val latitude: Float,
)

data class MainInfo(
    val temperature: Float,
)