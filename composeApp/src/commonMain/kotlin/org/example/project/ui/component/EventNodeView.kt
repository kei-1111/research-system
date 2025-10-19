package org.example.project.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.ktx.calcLeftTopOffset
import org.example.project.ktx.toDp
import org.example.project.ktx.toPx
import org.example.project.model.EventNode
import org.example.project.ui.theme.Shapes
import org.example.project.ui.theme.getEventColor
import org.jetbrains.compose.resources.painterResource

val EventNodeSize = 150.dp

@Composable
fun EventNodeView(
    eventNode: EventNode,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val eventColor = getEventColor(eventNode.event.eventType)
    val eventNodeSizePx = EventNodeSize.toPx()

    val event = eventNode.event
    val leftTopOffset = calcLeftTopOffset(eventNodeSizePx, eventNodeSizePx, eventNode.centerOffset)

    Surface(
        modifier = modifier
            .offset(x = leftTopOffset.x.toDp(), y = leftTopOffset.y.toDp()),
        shape = Shapes.medium,
        border = BorderStroke(
            width = 1.dp,
            color = eventColor.emphasis,
        ),
    ) {
        Column(
            modifier = Modifier
                .size(EventNodeSize)
                .clickable { onClick() },
        ) {
            Image(
                painter = painterResource(event.thumbnailImage),
                contentDescription = event.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = event.name,
                modifier = Modifier.fillMaxWidth(),
                color = eventColor.content,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}
