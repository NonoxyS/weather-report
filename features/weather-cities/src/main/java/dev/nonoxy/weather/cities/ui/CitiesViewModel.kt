package dev.nonoxy.weather.cities.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nonoxy.weather.cities.GetCitiesUseCase
import dev.nonoxy.weather.cities.models.CityUI
import dev.nonoxy.weather.cities.models.CoordinatesUI
import dev.nonoxy.weather.cities.ui.models.CitiesAction
import dev.nonoxy.weather.cities.ui.models.CitiesEvent
import dev.nonoxy.weather.cities.ui.models.CitiesViewState
import dev.nonoxy.weather.data.RequestResult
import dev.nonoxy.weather.theme.base.BaseViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : BaseViewModel<CitiesViewState, CitiesAction, CitiesEvent>(initialState = CitiesViewState.Loading) {

    init {
        fetchData()
    }

    override fun obtainEvent(viewEvent: CitiesEvent) {
        when (viewEvent) {
            is CitiesEvent.CityClicked -> cityClicked(city = viewEvent.city)
            CitiesEvent.ReloadData -> fetchData()
        }
    }

    private fun cityClicked(city: CityUI) {
        viewAction = CitiesAction.OpenWeatherReport(city)
    }

    private fun fetchData() {
        viewModelScope.launch {
            getCitiesUseCase.execute()
                .map { it.toCitiesViewState() }
                .collect { newState ->
                    viewState = newState
                }
        }
    }

    private fun getStartIndexes(entries: Map<Char, List<CityUI>>): List<Int> {
        var acc = 0
        val list = mutableListOf<Int>()
        entries.forEach { entry ->
            list.add(acc)
            acc += entry.value.size
        }
        return list
    }

    private fun getEndIndexes(entries: Map<Char, List<CityUI>>): List<Int> {
        var acc = 0
        val list = mutableListOf<Int>()
        entries.forEach { entry ->
            acc += entry.value.size
            list.add(acc)
        }
        return list
    }

    private fun RequestResult<List<CityUI>>.toCitiesViewState(): CitiesViewState {
        return when (this) {
            is RequestResult.Error -> CitiesViewState.Error
            is RequestResult.InProgress -> CitiesViewState.Loading
            is RequestResult.Success -> if (data.isNotEmpty()) {
                val sortedCities = data.sortedBy { it.cityName }
                val groupedCities = sortedCities.groupBy { it.cityName[0] }
                CitiesViewState.Display(
                    cities = sortedCities,
                    groupedCities = groupedCities,
                    startIndexes = getStartIndexes(groupedCities),
                    endIndexes = getEndIndexes(groupedCities)
                )
            } else CitiesViewState.Error
        }
    }
}