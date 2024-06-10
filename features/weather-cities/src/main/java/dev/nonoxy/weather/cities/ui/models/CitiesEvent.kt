package dev.nonoxy.weather.cities.ui.models

import dev.nonoxy.weather.cities.models.CityUI

sealed class CitiesEvent {
    data object ReloadData : CitiesEvent()
    data class CityClicked(val city: CityUI) : CitiesEvent()
}