package dev.nonoxy.weather.report.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.nonoxy.weather.cities.ui.CitiesScreen
import dev.nonoxy.weather_report.models.CityUI
import dev.nonoxy.weather_report.models.CoordinatesUI
import dev.nonoxy.weather_report.ui.WeatherReportScreen
import dev.nonoxy.weather_report.ui.WeatherReportViewModel
import dev.nonoxy.weather_report.ui.models.WeatherReportEvent

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Cities
    ) {
        composable<Screen.Cities> {
            CitiesScreen { city ->
                navController.navigate(
                    Screen.WeatherReport(
                        cityName = city.cityName,
                        latitude = city.coordinates.latitude,
                        longitude = city.coordinates.longitude
                    )
                )
            }
        }

        composable<Screen.WeatherReport> { backStackEntry ->
            val weatherReportViewModel: WeatherReportViewModel = hiltViewModel()
            val args = backStackEntry.toRoute<Screen.WeatherReport>()
            LaunchedEffect(key1 = args) {
                weatherReportViewModel.obtainEvent(
                    WeatherReportEvent.LoadData(
                        city = CityUI(
                            cityName = args.cityName,
                            coordinates = CoordinatesUI(
                                longitude = args.longitude,
                                latitude = args.latitude
                            )
                        )
                    )
                )
            }
            WeatherReportScreen(weatherReportViewModel)
        }
    }
}