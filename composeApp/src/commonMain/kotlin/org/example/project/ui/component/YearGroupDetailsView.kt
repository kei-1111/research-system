package org.example.project.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.data.population.PopulationEventType
import org.example.project.model.YearGroup
import org.example.project.ui.theme.EventColor
import org.example.project.ui.theme.getEventColor

@Suppress("MagicNumber")
private val eventNodeYears = mapOf(
    1907 to PopulationEventType.BigFire,
    1916 to PopulationEventType.BigFire,
    1934 to PopulationEventType.BigFire,
    1939 to PopulationEventType.Merger,
    1945 to PopulationEventType.AirRaid,
    1954 to PopulationEventType.Typhoon,
    1966 to PopulationEventType.Merger,
    1973 to PopulationEventType.Merger,
)

@Suppress("LongMethod", "CyclomaticComplexMethod")
@Composable
fun YearGroupDetailsView(
    yearGroup: YearGroup,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // EventNodeがある年かどうかを判定し、EventColorを取得
    val eventColor = eventNodeYears[yearGroup.year]?.let { eventType ->
        getEventColor(eventType)
    }
    Surface(
        modifier = modifier,
        color = Color.Transparent,
    ) {
        Surface(
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) { onDismiss() },
        ) {}

        Surface(
            modifier = Modifier
                .padding(
                    horizontal = 200.dp,
                    vertical = 100.dp,
                ),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(
                width = 2.dp,
                color = eventColor?.emphasis ?: MaterialTheme.colorScheme.onSurfaceVariant,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                YearGroupHeader(
                    year = yearGroup.year,
                    eventCount = yearGroup.events.size,
                    eventColor = eventColor,
                )

                YearGroupDetailsSection(
                    title = "特徴語",
                    eventColor = eventColor,
                    content = {
                        if (yearGroup.characteristicWords.isNotEmpty()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                yearGroup.characteristicWords.forEach { word ->
                                    Surface(
                                        shape = MaterialTheme.shapes.small,
                                        color = eventColor?.base ?: MaterialTheme.colorScheme.secondaryContainer,
                                        modifier = Modifier.padding(2.dp),
                                    ) {
                                        Text(
                                            text = word,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        )
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = "特徴語が見つかりませんでした。",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            )
                        }
                    },
                )

                YearGroupDetailsSection(
                    title = "歴史的出来事",
                    eventColor = eventColor,
                    content = {
                        if (yearGroup.events.isNotEmpty()) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                            ) {
                                yearGroup.events.forEach { event ->
                                    Surface(
                                        shape = MaterialTheme.shapes.small,
                                        color = Color.Transparent,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .border(
                                                width = 1.dp,
                                                color = eventColor?.base ?: MaterialTheme.colorScheme.onSurfaceVariant,
                                                shape = MaterialTheme.shapes.small,
                                            ),
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(12.dp),
                                            verticalArrangement = Arrangement.spacedBy(4.dp),
                                        ) {
                                            Text(
                                                text = "${event.jyear}${event.month?.let { "年${it}月" } ?: "年"}" +
                                                    "${event.day?.let { "${it}日" } ?: ""}",
                                                style = MaterialTheme.typography.labelMedium,
                                                color = eventColor?.content
                                                    ?: MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                            Text(
                                                text = event.text,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = eventColor?.content
                                                    ?: MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = "この年のイベントが見つかりませんでした。",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            )
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun YearGroupHeader(
    year: Int,
    eventCount: Int,
    modifier: Modifier = Modifier,
    eventColor: EventColor? = null,
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max),
    ) {
        Text(
            text = "${year}年の記録",
            color = eventColor?.content ?: MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineMedium,
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium),
            color = eventColor?.emphasis ?: MaterialTheme.colorScheme.onSurfaceVariant,
            thickness = 5.dp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "イベント数: ${eventCount}件",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun YearGroupDetailsSection(
    title: String,
    eventColor: EventColor?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = title,
            color = eventColor?.content ?: MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge,
        )
        content()
    }
}
