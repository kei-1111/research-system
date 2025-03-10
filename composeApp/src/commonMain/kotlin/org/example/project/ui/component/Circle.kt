package org.example.project.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun Circle(
    color: Color,
    size: Dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = color,
                shape = CircleShape,
            ),
    ) {}
}
