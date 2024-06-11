package dev.nonoxy.weather.data

import dev.nonoxy.weather.api.CitiesApi
import dev.nonoxy.weather.api.WeatherApi
import dev.nonoxy.weather.data.models.City
import dev.nonoxy.weather.data.models.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import java.util.UUID
import javax.inject.Inject

class WeatherReportRepository @Inject constructor(
    private val citiesApi: CitiesApi,
    private val weatherApi: WeatherApi
) {
    fun getCities(): Flow<RequestResult<List<City>>> {
        val apiRequest = flow { emit(citiesApi.getCities()) }.flowOn(Dispatchers.IO)
            .map { result ->
                result.toRequestResult()
                    .map { cities ->
                        // Считаю, что АПИ должно отдавать полные данные,
                        // поэтому города с пустым названием - просто не передаю дальше.
                        // Да, это может ухудшить пользовательский опыт, но, я считаю, что эта
                        // проблема должна решаться на стороне бэкенда
                        // Айди - далее использую только в качестве ключа для LazyColumn
                        // поэтому генерирую UUID
                        cities.filter { city ->
                            city.city.isNotBlank()
                        }.map { city ->
                            city.copy(id = city.id.ifBlank { generateUUID() }).toCity()
                        }
                    }
            }.flowOn(Dispatchers.Default)

        val start = flowOf<RequestResult<List<City>>>(RequestResult.InProgress())

        return merge(apiRequest, start)
    }

    fun getWeather(
        latitude: String,
        longitude: String,
    ): Flow<RequestResult<Weather>> {
        val apiRequest =
            flow {
                emit(weatherApi.getWeather(latitude = latitude, longitude = longitude))
            }.flowOn(Dispatchers.IO)
                .map { result ->
                    result.toRequestResult().map { it.toWeather() }
                }.flowOn(Dispatchers.Default)

        val start = flowOf<RequestResult<Weather>>(RequestResult.InProgress())

        return merge(apiRequest, start)
    }

    private fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }
}