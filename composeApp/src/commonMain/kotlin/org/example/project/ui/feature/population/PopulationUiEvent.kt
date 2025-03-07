package org.example.project.ui.feature.population

import androidx.compose.ui.geometry.Offset
import org.example.project.model.MajorEvent
import org.example.project.ui.base.UiEvent

sealed interface PopulationUiEvent : UiEvent {
    data class OnCharacteristicNodeHovered(val offset: Offset, val exception: String) : PopulationUiEvent
    data object OnCharacteristicNodeUnHovered : PopulationUiEvent
    data class OnEventNodeClicked(val clickedEvent: MajorEvent) : PopulationUiEvent
    data object OnEventNodeDetailsDismissed : PopulationUiEvent
}
