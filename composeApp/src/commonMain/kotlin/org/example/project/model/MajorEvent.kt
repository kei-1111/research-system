package org.example.project.model

import org.jetbrains.compose.resources.DrawableResource

data class MajorEvent(
    val gregorianCalender: Int,
    val japaneseCalendar: String,
    val eventType: EventType,
    val name: String,
    val characteristicWordMap: Map<String, String>,
    val exception: String,
    val thumbnailImage: DrawableResource,
    val mapImage: DrawableResource?,
    val photoImage: DrawableResource?,
    val postcardImage: DrawableResource?,
)
