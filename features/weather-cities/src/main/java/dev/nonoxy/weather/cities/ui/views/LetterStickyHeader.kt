package dev.nonoxy.weather.cities.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun LetterStickyHeader(char: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(40.dp), contentAlignment = Alignment.Center) {
        Text(
            text = char,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
        )
    }
}