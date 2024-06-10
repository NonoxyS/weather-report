package dev.nonoxy.weather.cities.ui.models

import dev.nonoxy.weather.cities.models.CityUI

sealed class CitiesAction {
    data class OpenWeatherReport(val city: CityUI) : CitiesAction()
}