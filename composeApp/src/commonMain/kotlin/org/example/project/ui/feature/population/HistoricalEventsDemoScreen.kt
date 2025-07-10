package org.example.project.ui.feature.population

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.model.HistoricalEvent
import org.example.project.ui.component.BodyMediumText
import org.example.project.ui.theme.dimensions.Paddings
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HistoricalEventDemoScreen() {
    val viewModel: PopulationViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    var filteredEvent by remember { mutableStateOf<List<HistoricalEvent>>(emptyList()) }

    if (uiState.isLoadingHistoricalEvents) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
        return
    }

    uiState.historicalEventsError?.let { error ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            BodyMediumText(text = "Error: $error")
        }
        return
    }

    // 初期値設定
    if (filteredEvent.isEmpty() && uiState.historicalEvents.isNotEmpty()) {
        filteredEvent = uiState.historicalEvents
    }

    Column {
        Row {
            Button(
                onClick = {
                    filteredEvent = uiState.historicalEvents.filter {
                        it.text.contains("大火") || it.source1?.contains("大火") == true
                    }
                },
            ) {
                BodyMediumText(
                    text = "大火",
                )
            }
            Button(
                onClick = {
                    filteredEvent = uiState.historicalEvents.filter {
                        it.text.contains("戦争") || it.source1?.contains("戦争") == true
                    }
                },
            ) {
                BodyMediumText(
                    text = "戦争",
                )
            }
            Button(
                onClick = {
                    filteredEvent = uiState.historicalEvents
                },
            ) {
                BodyMediumText(
                    text = "全て",
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Paddings.Medium),
        ) {
            items(filteredEvent) { event ->
                HistoricalEventItem(
                    historicalEvent = event,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun HistoricalEventItem(
    historicalEvent: HistoricalEvent,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(Paddings.Small),
        ) {
            BodyMediumText(
                text = historicalEvent.jyear,
            )
            BodyMediumText(
                text = historicalEvent.year.toString(),
            )
            BodyMediumText(
                text = historicalEvent.leapMonth.toString(),
            )
            historicalEvent.month?.let {
                BodyMediumText(
                    text = it.toString(),
                )
            }
            historicalEvent.day?.let {
                BodyMediumText(
                    text = it.toString(),
                )
            }
            historicalEvent.season?.let {
                BodyMediumText(
                    text = it,
                )
            }
            BodyMediumText(
                text = historicalEvent.eto,
            )
            historicalEvent.source1?.let {
                BodyMediumText(
                    text = it,
                )
            }
            historicalEvent.source2?.let {
                BodyMediumText(
                    text = it,
                )
            }
            historicalEvent.source3?.let {
                BodyMediumText(
                    text = it,
                )
            }
            historicalEvent.source4?.let {
                BodyMediumText(
                    text = it,
                )
            }
            historicalEvent.source5?.let {
                BodyMediumText(
                    text = it,
                )
            }
            historicalEvent.source6?.let {
                BodyMediumText(
                    text = it,
                )
            }
        }
        BodyMediumText(
            text = historicalEvent.text,
        )
    }
}
