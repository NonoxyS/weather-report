package dev.nonoxy.weather_report.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun WeatherReportLoadingView() {
    Box(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(48.dp))
    }
}