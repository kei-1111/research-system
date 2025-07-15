package org.example.project.ui.component

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.project.model.EventType
import org.example.project.ui.theme.getEventColor

val CharacteristicWordNodeBackgroundSize = 30.dp

@Composable
fun CharacteristicWordNodeView(
    eventType: EventType,
    word: String,
    onHover: () -> Unit,
    sendWidthPx: (Float) -> Unit,
    sendHeightPx: (Float) -> Unit,
    modifier: Modifier = Modifier,
    characteristicWordNodeBackgroundSize: Dp = CharacteristicWordNodeBackgroundSize,
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
        Circle(
            color = eventColor.base,
            size = characteristicWordNodeBackgroundSize,
        )
        Text(
            text = word,
            textAlign = TextAlign.Center,
            color = eventColor.content,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}
