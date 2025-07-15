@file:Suppress("MagicNumber")

package org.example.project.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import org.example.project.data.population.PopulationEventType
import org.example.project.model.EventType

val LocalEventColor = compositionLocalOf<EventColor> { error("Event Color Nothing") }

val BigFire = EventColor(
    base = Color(0xFFFBB077),
    emphasis = Color(0xFFE56300),
    content = Color(0xFF6A5C49),
)

val Merger = EventColor(
    base = Color(0xFFB0FB77),
    emphasis = Color(0xFF0B9307),
    content = Color(0xFF547353),
)

val AirRaid = EventColor(
    base = Color(0xFFA4A0C5),
    emphasis = Color(0xFF540793),
    content = Color(0xFF4D3E59),
)

val Typhoon = EventColor(
    base = Color(0xFFD0D0D0),
    emphasis = Color(0xFF3E3B41),
    content = Color(0xFF272727),
)

fun getEventColor(eventType: EventType): EventColor {
    return when (eventType) {
        PopulationEventType.BigFire -> BigFire
        PopulationEventType.Merger -> Merger
        PopulationEventType.AirRaid -> AirRaid
        PopulationEventType.Typhoon -> Typhoon
        else -> throw IllegalArgumentException("Unknown event type: $eventType")
    }
}

data class EventColor(
    val base: Color,
    val emphasis: Color,
    val content: Color,
)
