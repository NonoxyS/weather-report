package dev.nonoxy.weather.data

import dev.nonoxy.weather.api.models.CityDTO
import dev.nonoxy.weather.api.models.CoordinatesDTO
import dev.nonoxy.weather.api.models.MainInfoDTO
import dev.nonoxy.weather.api.models.WeatherDTO
import dev.nonoxy.weather.data.models.City
import dev.nonoxy.weather.data.models.Coordinates
import dev.nonoxy.weather.data.models.MainInfo
import dev.nonoxy.weather.data.models.Weather

internal fun CityDTO.toCity(): City {
    return City(
        id = id,
        city = city,
        latitude = latitude.toFloat(),
        longitude = longitude.toFloat(),
    )
}

internal fun WeatherDTO.toWeather(): Weather {
    return Weather(
        coordinates = coordinates.toCoordinates(),
        mainInfo = mainInfo.toMainInfo(),
    )
}

internal fun CoordinatesDTO.toCoordinates(): Coordinates {
    return Coordinates(
        longitude = longitude,
        latitude = latitude
    )
}

internal fun MainInfoDTO.toMainInfo(): MainInfo {
    return MainInfo(temperature = temperature)
}