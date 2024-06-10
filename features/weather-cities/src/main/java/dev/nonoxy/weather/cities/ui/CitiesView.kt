package dev.nonoxy.weather.cities.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.nonoxy.weather.cities.ui.models.CitiesEvent
import dev.nonoxy.weather.cities.ui.models.CitiesViewState
import dev.nonoxy.weather.cities.ui.views.CityItem
import dev.nonoxy.weather.cities.ui.views.LetterStickyHeader


@Composable
internal fun CitiesView(
    viewState: CitiesViewState.Display,
    eventHandler: (CitiesEvent) -> Unit,
) {
    Box(Modifier.fillMaxSize()) {

        val listState = rememberLazyListState()

        val needMoveStickyHeader by remember {
            derivedStateOf {
                viewState.endIndexes.contains(listState.firstVisibleItemIndex + 1)
            }
        }

        val letterStickyHeader by remember {
            derivedStateOf {
                viewState.cities[listState.firstVisibleItemIndex].cityName.first().toString()
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            itemsIndexed(viewState.cities, key = { _, city -> city.id }) { index, city ->
                val showLetter by remember {
                    derivedStateOf { viewState.startIndexes.contains(index) && listState.firstVisibleItemIndex != index }
                }
                CityItem(
                    city = city,
                    showCharHeader = showLetter,
                    navigateToWeather = {
                        eventHandler(
                            CitiesEvent.CityClicked(
                                city = city
                            )
                        )
                    }
                )
            }
        }
        LetterStickyHeader(
            char = letterStickyHeader,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .then(
                    if (needMoveStickyHeader) {
                        Modifier.offset {
                            IntOffset(0, -listState.firstVisibleItemScrollOffset)
                        }
                    } else {
                        Modifier
                    }
                )
        )
    }
}
