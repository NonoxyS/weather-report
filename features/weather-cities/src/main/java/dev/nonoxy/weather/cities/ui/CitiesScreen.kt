package dev.nonoxy.weather.cities.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import dev.nonoxy.weather.cities.models.CityUI
import dev.nonoxy.weather.cities.ui.models.CitiesAction
import dev.nonoxy.weather.cities.ui.models.CitiesEvent
import dev.nonoxy.weather.cities.ui.models.CitiesViewState
import dev.nonoxy.weather.cities.ui.views.CitiesErrorView
import dev.nonoxy.weather.cities.ui.views.CitiesLoadingView

@Composable
fun CitiesScreen(
    citiesViewModel: CitiesViewModel = hiltViewModel(),
    navigateToWeather: (CityUI) -> Unit,
) {
    val viewState by citiesViewModel.viewStates().collectAsState()
    val viewAction by citiesViewModel.viewActions().collectAsState(null)

    val lambdaRemember: (CitiesEvent) -> Unit = remember {
        { citiesViewModel.obtainEvent(it) }
    }

    when (val currentState = viewState) {
        is CitiesViewState.Display -> CitiesView(
            viewState = currentState,
            eventHandler = lambdaRemember
        )
        CitiesViewState.Error -> CitiesErrorView(eventHandler = lambdaRemember)
        CitiesViewState.Loading -> CitiesLoadingView()
    }

    when (viewAction) {
        is CitiesAction.OpenWeatherReport -> {
            navigateToWeather((viewAction as CitiesAction.OpenWeatherReport).city)
            citiesViewModel.clearAction()
        }
        null -> { }
    }
}