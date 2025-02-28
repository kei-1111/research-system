@file:Suppress("MagicNumber")

package org.example.project.ui.feature.population

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.Symbol
import io.github.koalaplot.core.line.AreaBaseline
import io.github.koalaplot.core.line.AreaPlot
import io.github.koalaplot.core.style.AreaStyle
import io.github.koalaplot.core.style.LineStyle
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.xygraph.IntLinearAxisModel
import io.github.koalaplot.core.xygraph.XYGraph
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.example.project.data.meiji40BigFire
import org.example.project.data.totalPopulation
import org.example.project.ktx.toDp
import org.example.project.ui.base.LocalData
import org.example.project.ui.component.BodySmallText
import org.example.project.ui.component.Event
import org.example.project.ui.component.TitleMediumText
import org.example.project.ui.theme.dimensions.Paddings
import org.example.project.utils.toPointList
import org.koin.compose.koinInject

@Suppress("ModifierMissing")
@Composable
fun PopulationScreen() {
    val viewModel = koinInject<PopulationViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val data = remember { totalPopulation.toPointList(Meiji5) }

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEvent.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { event ->
            when (event) {
                is PopulationUiEvent.OnCharacteristicNodeHovered -> viewModel.onCharacteristicNodeHovered(
                    event.offset,
                    event.exception,
                )
                is PopulationUiEvent.OnCharacteristicNodeUnHovered -> viewModel.onCharacteristicNodeUnHovered()
                is PopulationUiEvent.OnEventNodeClicked -> viewModel.onEventNodeClicked(event.clickedEvent)
            }
        }.launchIn(this)
    }

    CompositionLocalProvider(LocalData provides data) {
        PopulationScreen(
            uiState = uiState,
            onEvent = viewModel::onEvent,
        )
    }
}

@Suppress("LongMethod", "SpacingAroundCurly")
@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
private fun PopulationScreen(
    uiState: PopulationUiState,
    onEvent: (PopulationUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val data = LocalData.current

    Surface(
        modifier = modifier,
    ) {
        ChartLayout(
            modifier = Modifier
                .padding(Paddings.Small),
            title = { TitleMediumText("歴史的出来事可視化システム") },
        ) {
            XYGraph(
                xAxisModel = IntLinearAxisModel(data.minOf { it.x } ..data.maxOf { it.x }),
                yAxisModel = IntLinearAxisModel(0..350000),
                horizontalMinorGridLineStyle = null,
                verticalMinorGridLineStyle = null,
                zoomEnabled = true,
                allowIndependentZoom = true,
            ) {
                AreaPlot(
                    data = data,
                    lineStyle = LineStyle(brush = SolidColor(Color(0xFF37A78F)), strokeWidth = 2.dp),
                    areaStyle = AreaStyle(
                        brush = SolidColor(Color(0xFF37A78F)),
                        alpha = 0.5f,
                    ),
                    areaBaseline = AreaBaseline.ConstantLine(0),
                    symbol = { point ->
                        if (point.x == meiji40BigFire.gregorianCalender) {
                            Event(
                                event = meiji40BigFire,
                                onHover = { offset, exception ->
                                    onEvent(
                                        PopulationUiEvent.OnCharacteristicNodeHovered(
                                            offset,
                                            exception,
                                        ),
                                    )
                                },
                                onUnHover = { onEvent(PopulationUiEvent.OnCharacteristicNodeUnHovered) },
                                onClick = {
                                    onEvent(
                                        PopulationUiEvent.OnEventNodeClicked(
                                            meiji40BigFire,
                                        ),
                                    )
                                },
                            )
                        } else {
                            Symbol(
                                modifier = Modifier,
                            )
                        }
                    },
                )
            }
        }

        if (uiState.isCharacteristicNodeHovered) {
            uiState.characteristicNodeException?.let {
                Surface(
                    modifier = Modifier
                        .offset(x = it.offset.x.toDp(), y = it.offset.y.toDp()),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    BodySmallText(
                        text = it.exception,
                        modifier = Modifier.padding(Paddings.Small),
                    )
                }
            }
        }

        if (uiState.isShowEventNodeDetails) {
            uiState.showingEventNode?.let {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    BodySmallText(
                        text = "Clicked",
                        modifier = Modifier.padding(Paddings.Small),
                    )
                }
            }
        }
    }
}

private const val Meiji5 = 1872
