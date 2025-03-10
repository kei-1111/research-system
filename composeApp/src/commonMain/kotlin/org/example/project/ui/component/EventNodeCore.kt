package org.example.project.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.Symbol
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import org.example.project.ktx.calcCenterOffset
import org.example.project.ktx.toPx
import org.example.project.model.EventType
import org.example.project.ui.theme.LocalEventColor
import org.example.project.ui.theme.Shapes
import org.example.project.ui.theme.getEventColor

private val SymbolSize = 10.dp
private val BaseLeaderLineHeight = 50.dp

// 特徴語ノードで、頂点が隠れないように親ComposableにOffsetを送る
// このComposableが返すOffsetはCenterOffsetである
@Composable
fun EventNodeCore(
    eventType: EventType,
    sendEventNodeOffset: (Offset) -> Unit,
    sendSymbolOffset: (Offset) -> Unit,
    eventAttachPosition: EventAttachPosition,
    modifier: Modifier = Modifier,
    leaderLineHeight: Dp = BaseLeaderLineHeight + EventNodeSize / 2,
) {
    val eventColor = getEventColor(eventType)

    CompositionLocalProvider(LocalEventColor provides eventColor) {
        when (eventAttachPosition) {
            EventAttachPosition.Top -> {
                AttachTop(
                    sendEventNodeOffset = sendEventNodeOffset,
                    sendSymbolOffset = sendSymbolOffset,
                    modifier = modifier,
                    leaderLineHeight = leaderLineHeight,
                )
            }

            EventAttachPosition.Bottom -> {
                AttachBottom(
                    sendEventNodeOffset = sendEventNodeOffset,
                    sendSymbolOffset = sendSymbolOffset,
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

// EventNodeSizeの半分を足すことで、EventNodeSizeの中心まで引き出し線を伸ばす
@Suppress("LongParameterList")
@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
private fun AttachTop(
    sendEventNodeOffset: (Offset) -> Unit,
    sendSymbolOffset: (Offset) -> Unit,
    leaderLineHeight: Dp,
    modifier: Modifier = Modifier,
) {
    val eventColor = LocalEventColor.current

    var eventNodeOffset by remember { mutableStateOf(Offset.Zero) }
    var symbolOffset by remember { mutableStateOf(Offset.Zero) }

    val symbolSizePx = SymbolSize.toPx()

    val currentSendEventNodeOffset by rememberUpdatedState(sendEventNodeOffset)
    val currentSendSymbolOffset by rememberUpdatedState(sendSymbolOffset)

    LaunchedEffect(eventNodeOffset) {
        currentSendEventNodeOffset(
            calcCenterOffset(
                sizePx = symbolSizePx,
                leftTopOffset = eventNodeOffset,
            ),
        )
    }

    LaunchedEffect(symbolOffset) {
        currentSendSymbolOffset(
            calcCenterOffset(
                sizePx = symbolSizePx,
                leftTopOffset = symbolOffset,
            ),
        )
    }

    Column(
        modifier = modifier
//            yの値は、EventNodeの高さ（100.dp）と、引出線の長さ（50.dp）と、Symbolの高さ（10.dp）をすべて足し合わせて、2で割り、Symbolをグラフの線に合わせるために5.dpを足した値
//            ↑のようにした背景として、Symbolとして使用するものは、要素を中央揃えにされているからである。
//            グラフの頂点から見て上方向に、EventNodeを配置するため、offsetのyの値はマイナスになる。
            .offset(y = -(SymbolSize + leaderLineHeight + SymbolSize) / 2 + 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Symbol(
            shape = Shapes.small,
            size = SymbolSize,
            fillBrush = SolidColor(eventColor.emphasis),
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                eventNodeOffset = Offset(
                    x = layoutCoordinates.positionInWindow().x,
                    y = layoutCoordinates.positionInWindow().y,
                )
            },
        )
        VerticalDivider(
            modifier = Modifier.height(leaderLineHeight),
            thickness = 4.dp,
            color = eventColor.base,
        )
        Symbol(
            shape = MaterialTheme.shapes.small,
            size = SymbolSize,
            fillBrush = SolidColor(eventColor.emphasis),
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                symbolOffset = Offset(
                    x = layoutCoordinates.positionInWindow().x,
                    y = layoutCoordinates.positionInWindow().y,
                )
            },
        )
    }
}

// EventNodeSizeの半分を足すことで、EventNodeSizeの中心まで引き出し線を伸ばす
@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
private fun AttachBottom(
    sendEventNodeOffset: (Offset) -> Unit,
    sendSymbolOffset: (Offset) -> Unit,
    leaderLineHeight: Dp,
    modifier: Modifier = Modifier,
) {
    val eventColor = LocalEventColor.current

    var eventNodeOffset by remember { mutableStateOf(Offset.Zero) }
    var symbolOffset by remember { mutableStateOf(Offset.Zero) }

    val symbolSizePx = SymbolSize.toPx()

    val currentSendEventNodeOffset by rememberUpdatedState(sendEventNodeOffset)
    val currentSendSymbolOffset by rememberUpdatedState(sendSymbolOffset)

    LaunchedEffect(eventNodeOffset) {
        currentSendEventNodeOffset(
            calcCenterOffset(
                sizePx = symbolSizePx,
                leftTopOffset = eventNodeOffset,
            ),
        )
    }

    LaunchedEffect(symbolOffset) {
        currentSendSymbolOffset(
            calcCenterOffset(
                sizePx = symbolSizePx,
                leftTopOffset = symbolOffset,
            ),
        )
    }

    Column(
        modifier = modifier
//            yの値は、EventNodeの高さ（100.dp）と、引出線の長さ（50.dp）と、Symbolの高さ（10.dp）をすべて足し合わせて、2で割り、Symbolをグラフの線に合わせるために5.dpを引いた値
//            ↑のようにした背景として、Symbolとして使用するものは、要素を中央揃えにされているからである。
//            グラフの頂点から見て下方向に、EventNodeを配置するため、offsetのyの値はプラスになる。
            .offset(y = ((SymbolSize + leaderLineHeight + SymbolSize) / 2 - 5.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Symbol(
            shape = Shapes.small,
            size = SymbolSize,
            fillBrush = SolidColor(eventColor.emphasis),
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                symbolOffset = Offset(
                    x = layoutCoordinates.positionInWindow().x,
                    y = layoutCoordinates.positionInWindow().y,
                )
            },
        )
        VerticalDivider(
            modifier = Modifier.height(leaderLineHeight),
            thickness = 4.dp,
            color = eventColor.base,
        )
        Symbol(
            shape = Shapes.small,
            size = SymbolSize,
            fillBrush = SolidColor(eventColor.emphasis),
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                eventNodeOffset = Offset(
                    x = layoutCoordinates.positionInWindow().x,
                    y = layoutCoordinates.positionInWindow().y,
                )
            },
        )
    }
}
