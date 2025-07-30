package org.example.project.ui.feature.population

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.data.historicalevent.HistoricalEventsRepository
import org.example.project.model.MajorEvent
import org.example.project.model.YearGroup
import org.example.project.ui.base.BaseViewModel

class PopulationViewModel(
    private val historicalEventsRepository: HistoricalEventsRepository,
) : BaseViewModel<PopulationUiState, PopulationUiEvent>(
    initialState = PopulationUiState(),
) {
    private var autoUnHoverJob: Job? = null

    init {
        loadHistoricalEvents()
    }

    private fun loadHistoricalEvents() {
        updateUiState { it.copy(isLoadingYearGroups = true) }

        viewModelScope.launch {
            historicalEventsRepository.getYearGroups()
                .onSuccess { yearGroups ->
                    updateUiState {
                        it.copy(
                            yearGroups = yearGroups,
                            isLoadingYearGroups = false,
                            yearGroupsError = null,
                        )
                    }
                }
                .onFailure { error ->
                    updateUiState {
                        it.copy(
                            isLoadingYearGroups = false,
                            yearGroupsError = error.message,
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
            delay(AutoHoverDelayMillis)
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

    fun onYearGroupClicked(yearGroup: YearGroup) {
        updateUiState {
            it.copy(
                isShowYearGroupDetails = true,
                showingYearGroup = yearGroup,
            )
        }
    }

    fun onYearGroupDetailsDismissed() {
        updateUiState {
            it.copy(
                isShowYearGroupDetails = false,
                showingYearGroup = null,
            )
        }
    }

    private companion object {
        const val AutoHoverDelayMillis = 3000L
    }
}
