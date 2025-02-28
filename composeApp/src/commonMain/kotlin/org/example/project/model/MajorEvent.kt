package org.example.project.model

import org.jetbrains.compose.resources.DrawableResource

data class MajorEvent(
    val gregorianCalender: Int,
    val japaneseCalendar: String,
    val name: String,
    val thumbnailImage: DrawableResource,
    val characteristicWordMap: Map<String, String>,
)
