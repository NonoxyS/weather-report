package dev.nonoxy.weather.cities.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.nonoxy.weather.cities.models.CityUI

@Composable
internal fun CityItem(city: CityUI) {
    Row {
        Text(text = "${city.id}")
        Text(text = city.city)
    }
}