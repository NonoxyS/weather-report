package dev.nonoxy.weather.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    @SerialName("coord") val coordinates: CoordinatesDTO,
    @SerialName("main") val mainInfo: MainInfoDTO,
)

@Serializable
data class CoordinatesDTO(
    @SerialName("lon") val longitude: Float,
    @SerialName("lat") val latitude: Float,
)

@Serializable
data class MainInfoDTO(
    @SerialName("temp") val temperature: Float,
)