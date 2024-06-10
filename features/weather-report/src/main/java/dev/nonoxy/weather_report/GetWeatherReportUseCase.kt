package dev.nonoxy.weather_report

import dev.nonoxy.weather.data.RequestResult
import dev.nonoxy.weather.data.WeatherReportRepository
import dev.nonoxy.weather.data.map
import dev.nonoxy.weather.data.models.Weather
import dev.nonoxy.weather_report.models.CoordinatesUI
import dev.nonoxy.weather_report.models.MainInfoUI
import dev.nonoxy.weather_report.models.WeatherUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWeatherReportUseCase @Inject constructor(
    private val repository: WeatherReportRepository
) {
    internal fun execute(
        latitude: String,
        longitude: String
    ): Flow<RequestResult<WeatherUI>> {
        return repository.getWeather(latitude = latitude, longitude = longitude)
            .map { result ->
                result.map { weather -> weather.toWeatherUI() }
            }
    }

    private fun Weather.toWeatherUI(): WeatherUI {
        return WeatherUI(
            coordinates = CoordinatesUI(
                latitude = coordinates.latitude,
                longitude = coordinates.longitude
            ),
            mainInfo = MainInfoUI(
                temperature = mainInfo.temperature
            )
        )
    }
}
