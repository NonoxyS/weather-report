package dev.nonoxy.weather.cities

import dev.nonoxy.weather.cities.models.CityUI
import dev.nonoxy.weather.cities.models.CoordinatesUI
import dev.nonoxy.weather.data.RequestResult
import dev.nonoxy.weather.data.WeatherReportRepository
import dev.nonoxy.weather.data.map
import dev.nonoxy.weather.data.models.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: WeatherReportRepository
) {
    internal fun execute(): Flow<RequestResult<List<CityUI>>> {
        return repository.getCities()
            .map { result ->
                result.map { cities -> cities.map { it.toCityUI() } }
            }
    }

    private fun City.toCityUI(): CityUI {
        return CityUI(
            id = id,
            cityName = city,
            coordinates = CoordinatesUI(
                latitude = latitude,
                longitude = longitude
            )
        )
    }
}
