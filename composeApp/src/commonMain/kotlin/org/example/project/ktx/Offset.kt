package org.example.project.ktx

import androidx.compose.ui.geometry.Offset

fun calcMidpointOffset(
    startOffset: Offset,
    endOffset: Offset,
): Offset {
    val x = (startOffset.x + endOffset.x) / 2
    val y = (startOffset.y + endOffset.y) / 2
    return Offset(x, y)
}

fun sumOffset(
    offset1: Offset,
    offset2: Offset,
): Offset {
    val x = offset1.x + offset2.x
    val y = offset1.y + offset2.y
    return Offset(x, y)
}

fun calcCenterOffset(
    sizePx: Float,
    leftTopOffset: Offset,
): Offset {
    val x = leftTopOffset.x + sizePx / 2
    val y = leftTopOffset.y + sizePx / 2
    return Offset(x, y)
}

fun calcLeftTopOffset(
    widthPx: Float,
    heightPx: Float,
    centerOffset: Offset,
): Offset {
    val x = centerOffset.x - widthPx / 2
    val y = centerOffset.y - heightPx / 2
    return Offset(x, y)
}
