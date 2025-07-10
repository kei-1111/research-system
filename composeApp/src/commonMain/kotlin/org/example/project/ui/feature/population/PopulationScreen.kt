@file:Suppress("MagicNumber")

package org.example.project.ui.feature.population

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
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
import io.github.koalaplot.core.xygraph.TickPosition
import io.github.koalaplot.core.xygraph.XYGraph
import io.github.koalaplot.core.xygraph.rememberAxisStyle
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.example.project.data.population.PopulationRelatedEventId
import org.example.project.data.population.hakodateAirRaid
import org.example.project.data.population.kamedasiMerger
import org.example.project.data.population.meiji40BigFire
import org.example.project.data.population.populationRelatedCharacteristicWordList
import org.example.project.data.population.syowa9BigFire
import org.example.project.data.population.taisyo5BigFire
import org.example.project.data.population.totalPopulation
import org.example.project.data.population.toyamaruTyphoon
import org.example.project.data.population.yukawachoMerger
import org.example.project.data.population.zenikamesawamuraMerger
import org.example.project.ktx.calcMidpointOffset
import org.example.project.ktx.toDp
import org.example.project.ktx.toPx
import org.example.project.model.CharacteristicWordNode
import org.example.project.model.EventNode
import org.example.project.ui.base.LocalData
import org.example.project.ui.component.ConnectCharacteristicNode
import org.example.project.ui.component.EventAttachPosition
import org.example.project.ui.component.EventDetails
import org.example.project.ui.component.EventNodeCore
import org.example.project.ui.component.EventNodeSize
import org.example.project.ui.component.EventNodeView
import org.example.project.ui.component.LabelMediumText
import org.example.project.ui.theme.Typography
import org.example.project.ui.theme.dimensions.Paddings
import org.example.project.utils.toPointList
import org.koin.compose.koinInject
import kotlin.math.sqrt
import kotlin.random.Random

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
                    event.offsetList,
                    event.exceptionList,
                )

                is PopulationUiEvent.OnEventNodeClicked -> viewModel.onEventNodeClicked(event.clickedEvent)
                is PopulationUiEvent.OnEventNodeDetailsDismissed -> viewModel.onEventNodeDetailsDismissed()
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

// ここでのOffsetは基本的に、ノードの中心とする。
@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
private fun PopulationScreen(
    uiState: PopulationUiState,
    onEvent: (PopulationUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val data = LocalData.current

    var meiji40BigFireNode by remember { mutableStateOf(EventNode(meiji40BigFire)) }
    var taisyo5BigFireNode by remember { mutableStateOf(EventNode(taisyo5BigFire)) }
    var syowa9BigFireNode by remember { mutableStateOf(EventNode(syowa9BigFire)) }
    var yukawachoMergerNode by remember { mutableStateOf(EventNode(yukawachoMerger)) }
    var hakodateAirRaidNode by remember { mutableStateOf(EventNode(hakodateAirRaid)) }
    var toyamaruTyphoonNode by remember { mutableStateOf(EventNode(toyamaruTyphoon)) }
    var zenikamesawamuraMergerNode by remember { mutableStateOf(EventNode(zenikamesawamuraMerger)) }
    var kamedasiMergerNode by remember { mutableStateOf(EventNode(kamedasiMerger)) }

    val getPopulationRelatedEventNode: (PopulationRelatedEventId) -> EventNode = { id ->
        when (id) {
            PopulationRelatedEventId.Meiji40BigFire -> meiji40BigFireNode
            PopulationRelatedEventId.Taisyo5BigFire -> taisyo5BigFireNode
            PopulationRelatedEventId.Syowa9BigFire -> syowa9BigFireNode
            PopulationRelatedEventId.YukawachoMerger -> yukawachoMergerNode
            PopulationRelatedEventId.HakodateAirRaid -> hakodateAirRaidNode
            PopulationRelatedEventId.ToyamaruTyphoon -> toyamaruTyphoonNode
            PopulationRelatedEventId.ZenikamesawamuraMerger -> zenikamesawamuraMergerNode
            PopulationRelatedEventId.KamedasiMerger -> kamedasiMergerNode
        }
    }

    val nodeOffsetList = remember { mutableListOf<Offset>() }
    var yAxisLabelWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Surface(
        modifier = modifier,
    ) {
        ChartLayout(
            modifier = Modifier
                .padding(10.dp)
                .padding(end = yAxisLabelWidth + 4.dp),
        ) {
            XYGraph(
                xAxisModel = IntLinearAxisModel(data.minOf { it.x }..data.maxOf { it.x }),
                yAxisModel = IntLinearAxisModel(0..350000),
                xAxisStyle = rememberAxisStyle(
                    tickPosition = TickPosition.Inside
                ),
                yAxisStyle = rememberAxisStyle(
                    tickPosition = TickPosition.Inside,
                ),
                horizontalMinorGridLineStyle = null,
                verticalMinorGridLineStyle = null,
                xAxisLabels = @Composable { value ->
                    Text(
                        text = "${value}年",
                        style = Typography().bodyMedium,
                    )
                },
                yAxisLabels = @Composable { value ->
                    Text(
                        text = "${value / 10000}万",
                        style = Typography().bodyMedium,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .onGloballyPositioned {
                                val width = with(density) { it.size.width.toDp() }
                                if (width > yAxisLabelWidth) {
                                    yAxisLabelWidth = width
                                }
                            },
                    )
                },
            ) {
                AreaPlot(
                    data = data,
                    lineStyle = LineStyle(
                        brush = SolidColor(Color(0xFF37A78F)),
                        strokeWidth = 2.dp,
                    ),
                    areaStyle = AreaStyle(
                        brush = SolidColor(Color(0xFF37A78F)),
                        alpha = 0.5f,
                    ),
                    areaBaseline = AreaBaseline.ConstantLine(0),
                    symbol = { point ->
                        EventNodeCores(
                            gregorianCalender = point.x,
                            sendMeiji40BigFireOffset = {
                                meiji40BigFireNode = meiji40BigFireNode.copy(centerOffset = it)
                                nodeOffsetList.add(it)
                            },
                            sendTaisyo5BigFireOffset = {
                                taisyo5BigFireNode = taisyo5BigFireNode.copy(centerOffset = it)
                                nodeOffsetList.add(it)
                            },
                            sendSyowa9BigFireOffset = {
                                syowa9BigFireNode = syowa9BigFireNode.copy(centerOffset = it)
                                nodeOffsetList.add(it)
                            },
                            sendYukawachoMergerOffset = {
                                yukawachoMergerNode = yukawachoMergerNode.copy(centerOffset = it)
                                nodeOffsetList.add(it)
                            },
                            sendHakodateAirRaidOffset = {
                                hakodateAirRaidNode = hakodateAirRaidNode.copy(centerOffset = it)
                                nodeOffsetList.add(it)
                            },
                            sendToyamaruTyphoonOffset = {
                                toyamaruTyphoonNode = toyamaruTyphoonNode.copy(centerOffset = it)
                                nodeOffsetList.add(it)
                            },
                            sendZenikamesawamuraMergerOffset = {
                                zenikamesawamuraMergerNode =
                                    zenikamesawamuraMergerNode.copy(centerOffset = it)
                                nodeOffsetList.add(it)
                            },
                            sendKamedasiMergerOffset = {
                                kamedasiMergerNode = kamedasiMergerNode.copy(centerOffset = it)
                                nodeOffsetList.add(it)
                            },
                            sendSymbolOffset = {
                                nodeOffsetList.add(it)
                            },
                        )
                    },
                )
            }
        }

        Text(
            text = "函館市の人口推移",
            color = MaterialTheme.colorScheme.onSurface,
            style = Typography().headlineMedium,
            modifier = Modifier
                .padding(top = 36.dp)
                .padding(start = yAxisLabelWidth + 40.dp)
                .background(MaterialTheme.colorScheme.surface)
        )

        DisplayCharacteristicWordNode(
            nodeOffsetList = nodeOffsetList,
            getPopulationRelatedEventNode = getPopulationRelatedEventNode,
            onEvent = onEvent,
        )

        DisplayPopulationRelatedEventNode(
            meiji40BigFireNode = meiji40BigFireNode,
            taisyo5BigFireNode = taisyo5BigFireNode,
            syowa9BigFireNode = syowa9BigFireNode,
            yukawachoMergerNode = yukawachoMergerNode,
            hakodateAirRaidNode = hakodateAirRaidNode,
            toyamaruTyphoonNode = toyamaruTyphoonNode,
            zenikamesawamuraMergerNode = zenikamesawamuraMergerNode,
            kamedasiMergerNode = kamedasiMergerNode,
            onEvent = onEvent,
        )

        if (uiState.isCharacteristicNodeHovered) {
            uiState.characteristicNodeException?.let { exceptionList ->
                exceptionList.map {
                    Surface(
                        modifier = Modifier
                            .sizeIn(maxWidth = 250.dp)
                            .offset(x = it.offset.x.toDp(), y = it.offset.y.toDp()),
                        shape = MaterialTheme.shapes.medium,
                        shadowElevation = 5.dp,
                    ) {
                        LabelMediumText(
                            text = it.exception,
                            modifier = Modifier.padding(Paddings.Small),
                        )
                    }
                }
            }
        }

        if (uiState.isShowEventNodeDetails) {
            uiState.showingEventNode?.let {
                EventDetails(
                    event = it,
                    onDismiss = { onEvent(PopulationUiEvent.OnEventNodeDetailsDismissed) },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
private fun EventNodeCores(
    gregorianCalender: Int,
    sendMeiji40BigFireOffset: (Offset) -> Unit,
    sendTaisyo5BigFireOffset: (Offset) -> Unit,
    sendSyowa9BigFireOffset: (Offset) -> Unit,
    sendYukawachoMergerOffset: (Offset) -> Unit,
    sendHakodateAirRaidOffset: (Offset) -> Unit,
    sendToyamaruTyphoonOffset: (Offset) -> Unit,
    sendZenikamesawamuraMergerOffset: (Offset) -> Unit,
    sendKamedasiMergerOffset: (Offset) -> Unit,
    sendSymbolOffset: (Offset) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (gregorianCalender) {
        meiji40BigFire.gregorianCalender -> {
            EventNodeCore(
                eventType = meiji40BigFire.eventType,
                sendEventNodeOffset = sendMeiji40BigFireOffset,
                sendSymbolOffset = sendSymbolOffset,
                eventAttachPosition = EventAttachPosition.Bottom,
                modifier = modifier,
            )
        }

        taisyo5BigFire.gregorianCalender -> {
            EventNodeCore(
                eventType = taisyo5BigFire.eventType,
                sendEventNodeOffset = sendTaisyo5BigFireOffset,
                sendSymbolOffset = sendSymbolOffset,
                eventAttachPosition = EventAttachPosition.Top,
                modifier = modifier,
            )
        }

        syowa9BigFire.gregorianCalender -> {
            EventNodeCore(
                eventType = syowa9BigFire.eventType,
                sendEventNodeOffset = sendSyowa9BigFireOffset,
                sendSymbolOffset = sendSymbolOffset,
                eventAttachPosition = EventAttachPosition.Bottom,
                modifier = modifier,
            )
        }

        yukawachoMerger.gregorianCalender -> {
            EventNodeCore(
                eventType = yukawachoMerger.eventType,
                sendEventNodeOffset = sendYukawachoMergerOffset,
                sendSymbolOffset = sendSymbolOffset,
                eventAttachPosition = EventAttachPosition.Top,
                modifier = modifier,
            )
        }

        hakodateAirRaid.gregorianCalender -> {
            EventNodeCore(
                eventType = hakodateAirRaid.eventType,
                sendEventNodeOffset = sendHakodateAirRaidOffset,
                sendSymbolOffset = sendSymbolOffset,
                eventAttachPosition = EventAttachPosition.Bottom,
                modifier = modifier,
                leaderLineHeight = 200.dp,
            )
        }

        toyamaruTyphoon.gregorianCalender -> {
            EventNodeCore(
                eventType = toyamaruTyphoon.eventType,
                sendEventNodeOffset = sendToyamaruTyphoonOffset,
                sendSymbolOffset = sendSymbolOffset,
                eventAttachPosition = EventAttachPosition.Top,
                modifier = modifier,
                leaderLineHeight = 150.dp,
            )
        }

        zenikamesawamuraMerger.gregorianCalender -> {
            EventNodeCore(
                eventType = zenikamesawamuraMerger.eventType,
                sendEventNodeOffset = sendZenikamesawamuraMergerOffset,
                sendSymbolOffset = sendSymbolOffset,
                eventAttachPosition = EventAttachPosition.Bottom,
                modifier = modifier,
                leaderLineHeight = 200.dp,
            )
        }

        kamedasiMerger.gregorianCalender -> {
            EventNodeCore(
                eventType = kamedasiMerger.eventType,
                sendEventNodeOffset = sendKamedasiMergerOffset,
                sendSymbolOffset = sendSymbolOffset,
                eventAttachPosition = EventAttachPosition.Bottom,
                modifier = modifier,
            )
        }

        else -> {
            Symbol(
                modifier = Modifier,
            )
        }
    }
}

@Composable
private fun DisplayPopulationRelatedEventNode(
    meiji40BigFireNode: EventNode,
    taisyo5BigFireNode: EventNode,
    syowa9BigFireNode: EventNode,
    yukawachoMergerNode: EventNode,
    hakodateAirRaidNode: EventNode,
    toyamaruTyphoonNode: EventNode,
    zenikamesawamuraMergerNode: EventNode,
    kamedasiMergerNode: EventNode,
    onEvent: (PopulationUiEvent) -> Unit,
) {
    EventNodeView(
        eventNode = meiji40BigFireNode,
        onClick = { onEvent(PopulationUiEvent.OnEventNodeClicked(meiji40BigFireNode.event)) },
    )
    EventNodeView(
        eventNode = taisyo5BigFireNode,
        onClick = { onEvent(PopulationUiEvent.OnEventNodeClicked(taisyo5BigFireNode.event)) },
    )
    EventNodeView(
        eventNode = syowa9BigFireNode,
        onClick = { onEvent(PopulationUiEvent.OnEventNodeClicked(syowa9BigFireNode.event)) },
    )
    EventNodeView(
        eventNode = yukawachoMergerNode,
        onClick = { onEvent(PopulationUiEvent.OnEventNodeClicked(yukawachoMergerNode.event)) },
    )
    EventNodeView(
        eventNode = hakodateAirRaidNode,
        onClick = { onEvent(PopulationUiEvent.OnEventNodeClicked(hakodateAirRaidNode.event)) },
    )
    EventNodeView(
        eventNode = toyamaruTyphoonNode,
        onClick = { onEvent(PopulationUiEvent.OnEventNodeClicked(toyamaruTyphoonNode.event)) },
    )
    EventNodeView(
        eventNode = zenikamesawamuraMergerNode,
        onClick = { onEvent(PopulationUiEvent.OnEventNodeClicked(zenikamesawamuraMergerNode.event)) },
    )
    EventNodeView(
        eventNode = kamedasiMergerNode,
        onClick = { onEvent(PopulationUiEvent.OnEventNodeClicked(kamedasiMergerNode.event)) },
    )
}

@Composable
private fun DisplayCharacteristicWordNode(
    nodeOffsetList: MutableList<Offset>,
    getPopulationRelatedEventNode: (PopulationRelatedEventId) -> EventNode,
    onEvent: (PopulationUiEvent) -> Unit,
) {
    val characteristicWordNodeSizePx = 50.dp.toPx()
    val eventNodeSizePx = EventNodeSize.toPx()

    populationRelatedCharacteristicWordList.forEach { characteristicWord ->
        val eventNodeCenterOffset = characteristicWord.includeEvent.map {
            getPopulationRelatedEventNode(it.key as PopulationRelatedEventId).centerOffset
        }

        val eventType = getPopulationRelatedEventNode(
            characteristicWord.includeEvent.keys.first() as PopulationRelatedEventId,
        ).event.eventType

        val characteristicWordNodeOffset = if (eventNodeCenterOffset.size == 1) {
            generateCharacteristicWordNodeOffset(
                origin = eventNodeCenterOffset.single(),
                existingOffsets = nodeOffsetList,
                eventNodeSize = eventNodeSizePx + characteristicWordNodeSizePx,
                margin = 20f,
                collisionMinDistance = characteristicWordNodeSizePx,
            )
        } else {
            eventNodeCenterOffset.reduce(Offset::plus) / eventNodeCenterOffset.size.toFloat()
        }

        ConnectCharacteristicNode(
            eventType = eventType,
            eventNodeCenterOffset = eventNodeCenterOffset.toPersistentList(),
            onHover = {
                onEvent(
                    PopulationUiEvent.OnCharacteristicNodeHovered(
                        eventNodeCenterOffset.map {
                            calcMidpointOffset(
                                it,
                                characteristicWordNodeOffset
                            )
                        },
                        characteristicWord.includeEvent.map { it.value },
                    ),
                )
            },
            characteristicWordNode = CharacteristicWordNode(
                characteristicWord = characteristicWord,
                centerOffset = characteristicWordNodeOffset,
            ),
        )

        nodeOffsetList.add(characteristicWordNodeOffset)
    }
}

private const val Meiji5 = 1872

fun generateCharacteristicWordNodeOffset(
    origin: Offset,
    existingOffsets: List<Offset>,
    eventNodeSize: Float, // EventNodeの一辺のサイズ（px）
    margin: Float, // EventNodeの外側に確保する余白
    collisionMinDistance: Float,
    maxAttempts: Int = 1000,
): Offset {
    val halfEvent = eventNodeSize / 2
    // 生成可能な候補の領域は、中心から ±(半分のサイズ + margin)
    val halfOuter = halfEvent + margin

    for (i in 0 until maxAttempts) {
        // outerRect内からランダムな候補点を生成
        val randomX = Random.nextDouble(from = -halfOuter.toDouble(), until = halfOuter.toDouble())
        val randomY = Random.nextDouble(from = -halfOuter.toDouble(), until = halfOuter.toDouble())
        val candidate = Offset(origin.x + randomX.toFloat(), origin.y + randomY.toFloat())

        // 候補点がEventNode（中心originを持つ正方形）の内部に入っていないかチェック
        if (candidate.x in (origin.x - halfEvent)..(origin.x + halfEvent) &&
            candidate.y in (origin.y - halfEvent)..(origin.y + halfEvent)
        ) {
            continue
        }

        // 既存のノードとの衝突判定
        val isColliding = existingOffsets.any {
            distanceBetween(it, candidate) < collisionMinDistance
        }
        if (isColliding) continue

        return candidate
    }
    return Offset.Zero
}

private fun distanceBetween(offset1: Offset, offset2: Offset): Float {
    val dx = offset2.x - offset1.x
    val dy = offset2.y - offset1.y
    return sqrt(dx * dx + dy * dy)
}
