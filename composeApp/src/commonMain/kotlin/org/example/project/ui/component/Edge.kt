package org.example.project.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun Edge(
    startOffset: Offset,
    endOffset: Offset,
    color: Color,
    modifier: Modifier = Modifier,
    width: Float = 3f,
) {
    Canvas(
        modifier = modifier,
    ) {
        drawLine(
            color = color,
            start = startOffset,
            end = endOffset,
            strokeWidth = width,
        )
    }
}
