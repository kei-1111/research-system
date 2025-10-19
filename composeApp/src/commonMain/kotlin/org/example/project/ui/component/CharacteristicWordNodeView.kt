package org.example.project.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.model.EventType
import org.example.project.ui.theme.getEventColor

@Composable
fun CharacteristicWordNodeView(
    eventType: EventType,
    word: String,
    onHover: () -> Unit,
    sendWidthPx: (Float) -> Unit,
    sendHeightPx: (Float) -> Unit,
    characteristicWordNodeLevel: CharacteristicWordNodeLevel,
    modifier: Modifier = Modifier,
) {
    val eventColor = getEventColor(eventType)

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    if (isHovered) {
        onHover()
    }

    Box(
        modifier = modifier
            .hoverable(interactionSource = interactionSource)
            .onGloballyPositioned { layoutCoordinates ->
                sendWidthPx(layoutCoordinates.size.width.toFloat())
                sendHeightPx(layoutCoordinates.size.height.toFloat())
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = word,
            modifier = Modifier
                .background(
                    color = eventColor.base,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(horizontal = 8.dp)
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center,
            color = eventColor.content,
            style = when (characteristicWordNodeLevel) {
                CharacteristicWordNodeLevel.Level1 -> MaterialTheme.typography.labelMedium
                CharacteristicWordNodeLevel.Level2 -> MaterialTheme.typography.labelLarge
                CharacteristicWordNodeLevel.Level3 -> MaterialTheme.typography.bodySmall
            },
        )
    }
}

enum class CharacteristicWordNodeLevel {
    Level1,
    Level2,
    Level3,
}
