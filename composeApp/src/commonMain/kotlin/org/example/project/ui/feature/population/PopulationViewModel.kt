package org.example.project.ui.feature.population

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.data.historical_event.HistoricalEventsRepository
import org.example.project.model.MajorEvent
import org.example.project.ui.base.BaseViewModel

class PopulationViewModel(
    private val historicalEventsRepository: HistoricalEventsRepository
) : BaseViewModel<PopulationUiState, PopulationUiEvent>(
    initialState = PopulationUiState(),
) {
    private var autoUnHoverJob: Job? = null

    init {
        loadHistoricalEvents()
    }

    private fun loadHistoricalEvents() {
        updateUiState { it.copy(isLoadingHistoricalEvents = true) }
        
        viewModelScope.launch {
            historicalEventsRepository.getHistoricalEvents()
                .onSuccess { events ->
                    updateUiState {
                        it.copy(
                            historicalEvents = events,
                            isLoadingHistoricalEvents = false,
                            historicalEventsError = null
                        )
                    }
                }
                .onFailure { error ->
                    updateUiState {
                        it.copy(
                            isLoadingHistoricalEvents = false,
                            historicalEventsError = error.message
                        )
                    }
                }
        }
    }

    fun onCharacteristicNodeHovered(
        offsetList: List<Offset>,
        exceptionList: List<String>,
    ) {
        autoUnHoverJob?.cancel()

        updateUiState {
            it.copy(
                isCharacteristicNodeHovered = true,
                characteristicNodeException = offsetList.zip(exceptionList) { offset, exception ->
                    CharacteristicNodeException(offset, exception)
                },
            )
        }

        autoUnHoverJob = viewModelScope.launch {
            delay(3000)
            updateUiState {
                it.copy(
                    isCharacteristicNodeHovered = false,
                    characteristicNodeException = null,
                )
            }
        }
    }

    fun onEventNodeClicked(
        clickedEvent: MajorEvent,
    ) {
        updateUiState {
            it.copy(
                isShowEventNodeDetails = true,
                showingEventNode = clickedEvent,
            )
        }
    }

    fun onEventNodeDetailsDismissed() {
        updateUiState {
            it.copy(
                isShowEventNodeDetails = false,
                showingEventNode = null,
            )
        }
    }
}
