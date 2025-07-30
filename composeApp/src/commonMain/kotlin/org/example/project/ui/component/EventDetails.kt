package org.example.project.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableMap
import org.example.project.model.MajorEvent
import org.example.project.ui.theme.Shapes
import org.example.project.ui.theme.getEventColor
import org.example.project.utils.openUrl
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Suppress("LongMethod")
@Composable
fun EventDetails(
    event: MajorEvent,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val eventColor = getEventColor(event.eventType)

    Surface(
        modifier = modifier,
        color = Color.Transparent,
    ) {
        Background(
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier
                .fillMaxSize()
                .clickable { onDismiss() },
        )

        Surface(
            modifier = Modifier
                .padding(
                    horizontal = 200.dp,
                    vertical = 100.dp,
                ),
            shape = Shapes.medium,
            border = BorderStroke(
                width = 2.dp,
                color = eventColor.emphasis,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                EventHeader(
                    name = event.name,
                    textColor = eventColor.content,
                    dividerColor = eventColor.emphasis,
                )
                EventDetailsSection(
                    title = "概要",
                    textColor = eventColor.content,
                    content = {
                        Text(
                            text = event.exception,
                            color = eventColor.content,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                )
                EventDetailsSection(
                    title = "当時の記録",
                    textColor = eventColor.content,
                    content = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            EventImage(
                                title = "地図",
                                textColor = eventColor.content,
                                images = event.mapImages,
                            )
                            EventImage(
                                title = "写真",
                                textColor = eventColor.content,
                                images = event.photoImages,
                            )
                            EventImage(
                                title = "絵葉書",
                                textColor = eventColor.content,
                                images = event.postcardImages,
                            )
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun EventHeader(
    name: String,
    textColor: Color,
    dividerColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max),
    ) {
        Text(
            text = name,
            color = textColor,
            style = MaterialTheme.typography.headlineMedium,
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.medium),
            color = dividerColor,
            thickness = 5.dp,
        )
    }
}

@Composable
private fun EventDetailsSection(
    title: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = title,
            color = textColor,
            style = MaterialTheme.typography.titleLarge,
        )
        content()
    }
}

@Composable
private fun EventImage(
    title: String,
    textColor: Color,
    images: ImmutableMap<DrawableResource, String>?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = title,
            color = textColor,
            style = MaterialTheme.typography.titleMedium,
        )
        when (images) {
            null -> {
                Text(
                    text = "${title}画像がありませんでした。",
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            else -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    images.forEach { (image, url) ->
                        Image(
                            painter = painterResource(image),
                            contentDescription = title,
                            modifier = Modifier
                                .height(200.dp)
                                .clip(Shapes.small)
                                .clickable { openUrl(url) },
                        )
                    }
                }
            }
        }
    }
}
