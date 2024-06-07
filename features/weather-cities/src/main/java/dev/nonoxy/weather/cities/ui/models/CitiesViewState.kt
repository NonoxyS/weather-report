package dev.nonoxy.weather.cities.ui.models

import dev.nonoxy.weather.cities.models.CityUI

sealed class CitiesViewState {
    data object Loading : CitiesViewState()
    data object Error : CitiesViewState()

    data class Display(
        val cities: List<CityUI> = emptyList()
    ) : CitiesViewState()
}