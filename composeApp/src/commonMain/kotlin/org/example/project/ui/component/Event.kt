package org.example.project.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.Symbol
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import org.example.project.model.MajorEvent
import org.example.project.ui.theme.LocalEventColor
import org.example.project.ui.theme.getEventColor

@Suppress("LongParameterList")
@Composable
fun Event(
    event: MajorEvent,
    eventAttachPosition: EventAttachPosition,
    onHover: (Offset, String) -> Unit,
    onUnHover: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leaderLineHeight: Dp = 50.dp,
) {
    val eventColor = getEventColor(event.eventType)

    CompositionLocalProvider(LocalEventColor provides eventColor) {
        when (eventAttachPosition) {
            EventAttachPosition.Top -> {
                EventAttachTop(
                    event = event,
                    onHover = onHover,
                    onUnHover = onUnHover,
                    onClick = onClick,
                    modifier = modifier,
                    leaderLineHeight = leaderLineHeight,
                )
            }
            EventAttachPosition.Bottom -> {
                EventAttachBottom(
                    event = event,
                    onHover = onHover,
                    onUnHover = onUnHover,
                    onClick = onClick,
                    modifier = modifier,
                    leaderLineHeight = leaderLineHeight,
                )
            }
        }
    }
}

enum class EventAttachPosition {
    Top, Bottom
}

@Suppress("LongParameterList")
@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun EventAttachTop(
    event: MajorEvent,
    onHover: (Offset, String) -> Unit,
    onUnHover: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leaderLineHeight: Dp = 50.dp,
) {
    val eventColor = LocalEventColor.current

    Column(
        modifier = modifier
//            yの値は、EventNodeの高さ（100.dp）と、引出線の長さ（50.dp）と、Symbolの高さ（10.dp）をすべて足し合わせて、2で割り、Symbolをグラフの線に合わせるために5.dpを足した値
//            ↑のようにした背景として、Symbolとして使用するものは、要素を中央揃えにされているからである。
//            グラフの頂点から見て上方向に、EventNodeを配置するため、offsetのyの値はマイナスになる。
            .offset(y = (-(100 + leaderLineHeight.value + 10) / 2 + 5).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ConnectNodeWithLine(
            event = event,
            onHover = onHover,
            onUnHover = onUnHover,
            onClick = onClick,
        )
        VerticalDivider(
            modifier = Modifier.height(leaderLineHeight),
            thickness = 4.dp,
            color = eventColor.base,
        )
        Symbol(
            shape = MaterialTheme.shapes.small,
            size = 10.dp,
            fillBrush = SolidColor(eventColor.emphasis),
        )
    }
}

@Suppress("LongParameterList")
@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun EventAttachBottom(
    event: MajorEvent,
    onHover: (Offset, String) -> Unit,
    onUnHover: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leaderLineHeight: Dp = 50.dp,
) {
    val eventColor = LocalEventColor.current

    Column(
        modifier = modifier
//            yの値は、EventNodeの高さ（100.dp）と、引出線の長さ（50.dp）と、Symbolの高さ（10.dp）をすべて足し合わせて、2で割り、Symbolをグラフの線に合わせるために5.dpを引いた値
//            ↑のようにした背景として、Symbolとして使用するものは、要素を中央揃えにされているからである。
//            グラフの頂点から見て下方向に、EventNodeを配置するため、offsetのyの値はプラスになる。
            .offset(y = ((100 + leaderLineHeight.value + 10) / 2 - 5).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Symbol(
            shape = MaterialTheme.shapes.small,
            size = 10.dp,
            fillBrush = SolidColor(eventColor.emphasis),
        )
        VerticalDivider(
            modifier = Modifier.height(leaderLineHeight),
            thickness = 4.dp,
            color = eventColor.base,
        )
        ConnectNodeWithLine(
            event = event,
            onHover = onHover,
            onUnHover = onUnHover,
            onClick = onClick,
        )
    }
}
