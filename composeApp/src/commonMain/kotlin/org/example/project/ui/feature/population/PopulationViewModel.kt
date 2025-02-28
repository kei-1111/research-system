package org.example.project.ui.feature.population

import androidx.compose.ui.geometry.Offset
import org.example.project.model.MajorEvent
import org.example.project.ui.base.BaseViewModel

class PopulationViewModel : BaseViewModel<PopulationUiState, PopulationUiEvent>(
    initialState = PopulationUiState(),
) {
    fun onCharacteristicNodeHovered(
        offset: Offset,
        exception: String,
    ) {
        updateUiState {
            it.copy(
                isCharacteristicNodeHovered = true,
                characteristicNodeException = CharacteristicNodeException(
                    offset = offset,
                    exception = exception,
                ),
            )
        }
    }

    fun onCharacteristicNodeUnHovered() {
        updateUiState {
            it.copy(
                isCharacteristicNodeHovered = false,
                characteristicNodeException = null,
            )
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
}
