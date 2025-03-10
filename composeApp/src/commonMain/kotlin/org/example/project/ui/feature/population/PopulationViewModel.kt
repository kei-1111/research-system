package org.example.project.ui.feature.population

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.model.MajorEvent
import org.example.project.ui.base.BaseViewModel

class PopulationViewModel : BaseViewModel<PopulationUiState, PopulationUiEvent>(
    initialState = PopulationUiState(),
) {
    private var autoUnHoverJob: Job? = null

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
