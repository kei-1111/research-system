package org.example.project.model

import androidx.compose.ui.geometry.Offset

data class CharacteristicWord(
    val word: String,
    val includeEvent: Map<Event, String>,
)

data class CharacteristicWordNode(
    val characteristicWord: CharacteristicWord,
    val centerOffset: Offset,
)
