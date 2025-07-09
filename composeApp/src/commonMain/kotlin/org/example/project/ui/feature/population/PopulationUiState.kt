package org.example.project.ui.feature.population

import androidx.compose.ui.geometry.Offset
import org.example.project.model.HistoricalEvent
import org.example.project.model.MajorEvent
import org.example.project.ui.base.UiState

data class PopulationUiState(
    val isCharacteristicNodeHovered: Boolean = false,
    val characteristicNodeException: List<CharacteristicNodeException>? = null,
    val isShowEventNodeDetails: Boolean = false,
    val showingEventNode: MajorEvent? = null,
    val historicalEvents: List<HistoricalEvent> = emptyList(),
    val isLoadingHistoricalEvents: Boolean = false,
    val historicalEventsError: String? = null,
) : UiState

data class CharacteristicNodeException(
    val offset: Offset,
    val exception: String,
)
