package org.example.project.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.model.MajorEvent
import org.example.project.ui.theme.Shapes
import org.jetbrains.compose.resources.painterResource

@Composable
fun EventNode(
    event: MajorEvent,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = Shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .size(100.dp)
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
            BodyMediumText(
                text = event.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}
