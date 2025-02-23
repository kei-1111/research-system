package org.example.project.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.ktx.toPx
import org.example.project.model.MajorEvent

@Composable
fun ConnectNodeWithLine(
    event: MajorEvent,
    modifier: Modifier = Modifier,
) {
    val eventNodeCenterPositionX = 50.dp.toPx()
    val eventNodeCenterPositionY = 50.dp.toPx()

    val characteristicNodeCenterPositionX = 25.dp.toPx()
    val characteristicNodeCenterPositionY = 25.dp.toPx()

    val startPosition by remember {
        mutableStateOf(
            Offset(
                x = eventNodeCenterPositionX,
                y = eventNodeCenterPositionY
            )
        )
    }

    Box(
        modifier = modifier,
    ) {
        event.characteristicWordList.forEachIndexed { index, word ->
            val characteristicNodePositionX = characteristicNodePositionXList[index]
            val characteristicNodePositionY = characteristicNodePositionYList[index]
            val characteristicNodePositionXPx = characteristicNodePositionX.toPx()
            val characteristicNodePositionYPx = characteristicNodePositionY.toPx()

            val endPosition by remember {
                mutableStateOf(
                    Offset(
                        x = characteristicNodePositionXPx + characteristicNodeCenterPositionX,
                        y = characteristicNodePositionYPx + characteristicNodeCenterPositionY
                    )
                )
            }

            Canvas(
                modifier = Modifier
            ) {
                drawLine(
                    color = Color.Black,
                    start = startPosition,
                    end = endPosition,
                    strokeWidth = 5f
                )
            }

            CharacteristicNode(
                word = word,
                modifier = Modifier
                    .offset(x = characteristicNodePositionX, y = characteristicNodePositionY)
            )
        }

        EventNode(
            event = event,
        )
    }
}

private val characteristicNodePositionXList = listOf(
    75.dp,
    125.dp,
    175.dp,
    125.dp,
    (-25).dp,
    (-75).dp,
    (-125).dp,
    (-75).dp,
)

private val characteristicNodePositionYList = listOf(
    (-100).dp,
    (-50).dp,
    0.dp,
    50.dp,
    (-100).dp,
    (-50).dp,
    0.dp,
    50.dp,
)
