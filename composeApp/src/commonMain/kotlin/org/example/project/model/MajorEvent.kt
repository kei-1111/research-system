package org.example.project.model

import kotlinx.collections.immutable.ImmutableMap
import org.jetbrains.compose.resources.DrawableResource

data class MajorEvent(
    val gregorianCalender: Int,
    val japaneseCalendar: String,
    val eventType: EventType,
    val name: String,
    val characteristicWordMap: ImmutableMap<String, String>,
    val exception: String,
    val thumbnailImage: DrawableResource,
    val mapImages: ImmutableMap<DrawableResource, String>?,
    val photoImages: ImmutableMap<DrawableResource, String>?,
    val postcardImages: ImmutableMap<DrawableResource, String>?,
)
