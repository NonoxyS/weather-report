package dev.nonoxy.weather_report.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import dev.nonoxy.weather_report.ui.models.WeatherReportEvent
import dev.nonoxy.weather_report.ui.models.WeatherReportViewState
import dev.nonoxy.weather_report.ui.views.WeatherReportErrorView
import dev.nonoxy.weather_report.ui.views.WeatherReportLoadingView

@Composable
fun WeatherReportScreen (
    weatherReportViewModel: WeatherReportViewModel,
) {
    val viewState by weatherReportViewModel.viewStates().collectAsState()
    val lambdaRemember: (WeatherReportEvent) -> Unit = remember {
        { weatherReportViewModel.obtainEvent(it) }
    }

    when (val currentState = viewState) {
        is WeatherReportViewState.Display -> WeatherReportView(
            viewState = currentState,
            eventHandler = lambdaRemember
        )
        is WeatherReportViewState.Error -> WeatherReportErrorView(
            viewState = currentState,
            eventHandler = lambdaRemember
        )
        WeatherReportViewState.Loading -> WeatherReportLoadingView()
    }
}