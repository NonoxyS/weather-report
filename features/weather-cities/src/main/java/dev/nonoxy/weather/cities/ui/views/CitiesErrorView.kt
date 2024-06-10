package dev.nonoxy.weather.cities.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.nonoxy.weather.cities.R
import dev.nonoxy.weather.cities.ui.models.CitiesEvent
import dev.nonoxy.weather.theme.ui.WeatherReportTheme

@Composable
internal fun CitiesErrorView(
    eventHandler: (CitiesEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(42.dp)
        ) {
            Text(
                text = stringResource(R.string.error),
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            TextButton(
                onClick = { eventHandler(CitiesEvent.ReloadData) },
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
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark")
private fun CitiesError_Preview() {
    WeatherReportTheme {
        Surface {
            CitiesErrorView(eventHandler = {})
        }
    }
}