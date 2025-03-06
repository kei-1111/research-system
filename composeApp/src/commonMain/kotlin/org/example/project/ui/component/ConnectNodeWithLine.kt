package org.example.project.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.dp
import org.example.project.ktx.calcCenterOffset
import org.example.project.ktx.sumOffset
import org.example.project.ktx.toPx
import org.example.project.model.MajorEvent
import org.example.project.ui.theme.LocalEventColor

@Suppress("LongMethod")
@Composable
fun ConnectNodeWithLine(
    event: MajorEvent,
    onHover: (Offset, String) -> Unit,
    onUnHover: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val eventColor = LocalEventColor.current

    val eventNodeCenterPosition = Pair(50.dp.toPx(), 50.dp.toPx())

    val characteristicNodeCenterPosition = Pair(25.dp.toPx(), 25.dp.toPx())

    val startPosition by remember {
        mutableStateOf(
            Offset(
                x = eventNodeCenterPosition.first,
                y = eventNodeCenterPosition.second,
            ),
        )
    }

    var globalPosition by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .onGloballyPositioned { layoutCoordinates ->
                globalPosition = Offset(
                    x = layoutCoordinates.positionInWindow().x,
                    y = layoutCoordinates.positionInWindow().y,
                )
            },
    ) {
        event.characteristicWordMap.entries.forEachIndexed { index, (key, value) ->
            val characteristicNodePosition = characteristicNodePositionList[index]
            val characteristicNodePositionXPx = characteristicNodePosition.x.dp.toPx()
            val characteristicNodePositionYPx = characteristicNodePosition.y.dp.toPx()

            val endPosition by remember {
                mutableStateOf(
                    Offset(
                        x = characteristicNodePositionXPx + characteristicNodeCenterPosition.first,
                        y = characteristicNodePositionYPx + characteristicNodeCenterPosition.second,
                    ),
                )
            }

            val centerPosition by remember { mutableStateOf(calcCenterOffset(startPosition, endPosition)) }

            Canvas(
                modifier = Modifier,
            ) {
                drawLine(
                    color = eventColor.content,
                    start = startPosition,
                    end = endPosition,
                    strokeWidth = 5f,
                )
            }

            CharacteristicNode(
                word = key,
                onHover = { onHover(sumOffset(centerPosition, globalPosition), value) },
                onUnHover = { onUnHover() },
                modifier = Modifier
                    .offset(x = characteristicNodePosition.x.dp, y = characteristicNodePosition.y.dp),
            )
        }

        EventNode(
            event = event,
            onClick = onClick,
        )
    }
}

// 中心分の座標が左上に寄っているため、中心分を加算して中心にする
@Suppress("MagicNumber")
private val characteristicNodePositionList = listOf(
    Offset(75f, -70f),
    Offset(125f, -35f),
    Offset(125f, 35f),
    Offset(100f, 100f),
    Offset(-25f, -70f),
    Offset(-75f, -35f),
    Offset(-75f, 35f),
    Offset(-50f, 100f),
)
