package dev.nonoxy.weather_report.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.nonoxy.weather.report.R
import dev.nonoxy.weather.theme.ui.WeatherReportTheme
import dev.nonoxy.weather_report.models.CityUI
import dev.nonoxy.weather_report.models.CoordinatesUI
import dev.nonoxy.weather_report.ui.models.WeatherReportEvent
import dev.nonoxy.weather_report.ui.models.WeatherReportViewState

@Composable
internal fun WeatherReportView(
    viewState: WeatherReportViewState.Display,
    eventHandler: (WeatherReportEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 40.dp, bottom = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${viewState.temperature}°C",
            style = MaterialTheme.typography.displayLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = viewState.cityName,
            style = MaterialTheme.typography.displayMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = {
                eventHandler(
                    WeatherReportEvent.LoadData(
                        city = CityUI(
                            cityName = viewState.cityName,
                            coordinates = viewState.coordinates
                        )
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.reload),
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark")
private fun WeatherReport_Preview() {
    WeatherReportTheme {
        Surface {
            WeatherReportView(
                viewState = WeatherReportViewState.Display(
                    temperature = 23,
                    cityName = "Дубна",
                    coordinates = CoordinatesUI(
                        latitude = 11f,
                        longitude = 11f
                    )
                ),
                eventHandler = {}
            )
        }
    }
}