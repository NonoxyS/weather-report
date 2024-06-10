package dev.nonoxy.weather.cities.ui.models

import androidx.compose.runtime.Stable
import dev.nonoxy.weather.cities.models.CityUI

sealed class CitiesViewState {
    data object Loading : CitiesViewState()
    data object Error : CitiesViewState()

    @Stable
    data class Display(
        val cities: List<CityUI> = emptyList(),
        val groupedCities: Map<Char, List<CityUI>> = emptyMap(),
        val startIndexes: List<Int>,
        val endIndexes: List<Int>,
    ) : CitiesViewState()
}