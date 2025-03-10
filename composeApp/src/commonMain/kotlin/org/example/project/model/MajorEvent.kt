package org.example.project.model

import androidx.compose.ui.geometry.Offset
import kotlinx.collections.immutable.ImmutableMap
import org.jetbrains.compose.resources.DrawableResource

data class MajorEvent(
    val gregorianCalender: Int,
    val japaneseCalendar: String,
    val eventType: EventType,
    val id: Event,
    val name: String,
    val exception: String,
    val thumbnailImage: DrawableResource,
    val mapImages: ImmutableMap<DrawableResource, String>?,
    val photoImages: ImmutableMap<DrawableResource, String>?,
    val postcardImages: ImmutableMap<DrawableResource, String>?,
)

data class EventNode(
    val event: MajorEvent,
    val centerOffset: Offset = Offset.Zero,
)
