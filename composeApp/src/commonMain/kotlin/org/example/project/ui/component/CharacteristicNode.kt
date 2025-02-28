package org.example.project.ui.component

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CharacteristicNode(
    word: String,
    onHover: () -> Unit,
    onUnHover: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    if (isHovered) {
        onHover()
    } else {
        onUnHover()
    }

    Box(
        modifier = modifier
            .hoverable(interactionSource = interactionSource),
        contentAlignment = Alignment.Center,
    ) {
        Circle()
        BodySmallText(
            text = word,
            textAlign = TextAlign.Center,
        )
    }
}
