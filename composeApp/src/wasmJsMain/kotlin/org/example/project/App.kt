package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import io.github.koalaplot.core.bar.BulletGraphs
import io.github.koalaplot.core.style.KoalaPlotTheme
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.xygraph.FloatLinearAxisModel
import org.jetbrains.compose.resources.painterResource
import research_system.composeapp.generated.resources.Res
import research_system.composeapp.generated.resources.compose_multiplatform

@Suppress("ModifierMissing")
@Composable
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

@Suppress("MagicNumber")
@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun GraphSample(
    modifier: Modifier = Modifier,
) {
    MaterialTheme {
        Column(
            modifier = modifier,
        ) {
            Text(
                text = "Hello, World!",
            )
            BulletGraphs(
                modifier = Modifier.fillMaxSize(),
            ) {
                bullet(FloatLinearAxisModel(0f..300f)) {
                    label {
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.padding(end = KoalaPlotTheme.sizes.gap),
                        ) {
                            Text("Revenue 2005 YTD", textAlign = TextAlign.End)
                            Text("(US $ in thousands)", textAlign = TextAlign.End)
                        }
                    }
                    axis { labels { Text("${it.toInt()}") } }
                    comparativeMeasure(260f)
                    featuredMeasureBar(275f)
                    ranges(0f, 200f, 250f, 300f)
                }
            }
        }
    }
}
