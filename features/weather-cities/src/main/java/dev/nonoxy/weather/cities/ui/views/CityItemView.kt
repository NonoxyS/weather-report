package dev.nonoxy.weather.cities.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.nonoxy.weather.cities.models.CityUI
import dev.nonoxy.weather.cities.models.CoordinatesUI
import dev.nonoxy.weather.theme.ui.WeatherReportTheme

@Composable
internal fun CityItem(
    city: CityUI,
    showCharHeader: Boolean,
    navigateToWeather: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .height(40.dp)
    ) {
        if (showCharHeader) {
            LetterStickyHeader(
                char = city.cityName.first().toString(),
                modifier = Modifier
            )
        } else {
            Spacer(modifier = Modifier.width(40.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                .clickable { navigateToWeather() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = city.cityName,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark")
private fun CityItem_Preview() {
    WeatherReportTheme {
        Surface {
            CityItem(
                city = CityUI(
                    id = "123",
                    cityName = "Дубна",
                    coordinates = CoordinatesUI(
                        latitude = 11f,
                        longitude = 11f
                    )
                ),
                showCharHeader = true,
                navigateToWeather = {}
            )
        }
    }
}