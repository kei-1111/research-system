package org.example.project.ktx

import androidx.compose.ui.geometry.Offset

fun calcCenterOffset(
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
