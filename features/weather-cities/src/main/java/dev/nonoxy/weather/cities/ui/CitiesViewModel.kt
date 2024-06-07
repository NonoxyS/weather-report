package dev.nonoxy.weather.cities.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nonoxy.weather.cities.GetCitiesUseCase
import dev.nonoxy.weather.cities.models.CityUI
import dev.nonoxy.weather.cities.ui.models.CitiesAction
import dev.nonoxy.weather.cities.ui.models.CitiesEvent
import dev.nonoxy.weather.cities.ui.models.CitiesViewState
import dev.nonoxy.weather.data.RequestResult
import dev.nonoxy.weather.theme.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
): BaseViewModel<CitiesViewState, CitiesAction, CitiesEvent>(initialState = CitiesViewState.Loading) {

    init {
        viewModelScope.launch {
            getCitiesUseCase.execute()
                .map { it.toCitiesViewState() }
                .collect { newState ->
                    viewState = newState
                }
        }
    }
    override fun obtainEvent(viewEvent: CitiesEvent) {
        TODO("Not yet implemented")
    }

    private fun RequestResult<List<CityUI>>.toCitiesViewState(): CitiesViewState {
        return when (this) {
            is RequestResult.Error -> CitiesViewState.Error
            is RequestResult.InProgress -> CitiesViewState.Loading
            is RequestResult.Success -> CitiesViewState.Display(cities = data)
        }
    }
}