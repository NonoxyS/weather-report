package dev.nonoxy.weather.cities.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CitiesScreen(
    citiesViewModel: CitiesViewModel = hiltViewModel(),
    navigateToWeather: () -> Unit,
) {
    val viewState = citiesViewModel.viewStates().collectAsState()
    val viewAction = citiesViewModel.viewActions().collectAsState(null)


}