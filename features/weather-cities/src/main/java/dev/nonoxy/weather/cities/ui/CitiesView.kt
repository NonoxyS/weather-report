package dev.nonoxy.weather.cities.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.nonoxy.weather.cities.models.CityUI
import dev.nonoxy.weather.cities.ui.models.CitiesEvent
import dev.nonoxy.weather.cities.ui.models.CitiesViewState
import dev.nonoxy.weather.theme.ui.WeatherReportTheme

@Composable
internal fun CitiesView(
    viewState: CitiesViewState,
    eventHandler: (CitiesEvent) -> Unit
) {
    LazyColumn {

    }
}

@Composable
@Preview
private fun CitiesView_Preview() {
    WeatherReportTheme {
        Surface {
            CitiesView(
                viewState = CitiesViewState.Display(
                    cities = listOf(
                        CityUI(
                            id = 141980,
                            city = "Дубна",
                            latitude = 56.7417949,
                            longitude = 37.1757163
                        )
                    )
                ),
                eventHandler = {}
            )
        }
    }
}