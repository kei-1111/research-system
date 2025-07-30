@file:Suppress("MagicNumber")

package org.example.project.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import kotlinx.collections.immutable.ImmutableList
import org.example.project.ktx.calcLeftTopOffset
import org.example.project.ktx.toDp
import org.example.project.ktx.toPx
import org.example.project.model.CharacteristicWordNode
import org.example.project.model.EventType
import org.example.project.ui.theme.getEventColor

@Composable
fun ConnectCharacteristicNode(
    eventType: EventType,
    eventNodeCenterOffset: ImmutableList<Offset>,
    characteristicWordNode: CharacteristicWordNode,
    onHover: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val eventColor = getEventColor(eventType)

    val characteristicWordNodeBackgroundSizePx =
        (CharacteristicWordNodeBackgroundSize.toPx() * (1 + (0.5f * (eventNodeCenterOffset.size - 1))))

    var characteristicWordNodeWidthPx by remember { mutableFloatStateOf(0f) }
    var characteristicWordNodeHeightPx by remember { mutableFloatStateOf(0f) }

    val characteristicWord = characteristicWordNode.characteristicWord
    val leftTopOffset =
        calcLeftTopOffset(
            characteristicWordNodeWidthPx,
            characteristicWordNodeHeightPx,
            characteristicWordNode.centerOffset,
        )

    Box(
        modifier = modifier,
    ) {
        eventNodeCenterOffset.forEach { offset ->
            Edge(
                startOffset = offset,
                endOffset = characteristicWordNode.centerOffset,
                color = eventColor.base,
            )
        }

        CharacteristicWordNodeView(
            eventType = eventType,
            word = characteristicWord.word,
            onHover = onHover,
            sendWidthPx = { characteristicWordNodeWidthPx = it },
            sendHeightPx = { characteristicWordNodeHeightPx = it },
            characteristicWordNodeBackgroundSize = characteristicWordNodeBackgroundSizePx.toDp(),
            modifier = Modifier
                .offset(x = leftTopOffset.x.toDp(), y = leftTopOffset.y.toDp()),
        )
    }
}
