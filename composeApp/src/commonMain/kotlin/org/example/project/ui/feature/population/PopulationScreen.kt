@file:Suppress("MagicNumber")

package org.example.project.ui.feature.population

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
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
import io.github.koalaplot.core.xygraph.Point
import io.github.koalaplot.core.xygraph.TickPosition
import io.github.koalaplot.core.xygraph.XYGraph
import io.github.koalaplot.core.xygraph.rememberAxisStyle
import io.github.koalaplot.core.xygraph.rememberIntLinearAxisModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.example.project.data.population.PopulationEventType
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
import org.example.project.model.YearGroup
import org.example.project.ui.base.LocalData
import org.example.project.ui.component.ConnectCharacteristicNode
import org.example.project.ui.component.EventAttachPosition
import org.example.project.ui.component.EventDetails
import org.example.project.ui.component.EventNodeCore
import org.example.project.ui.component.EventNodeSize
import org.example.project.ui.component.EventNodeView
import org.example.project.ui.component.YearGroupDetailsView
import org.example.project.ui.theme.EventColor
import org.example.project.ui.theme.getEventColor
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
                is PopulationUiEvent.OnYearGroupClicked -> viewModel.onYearGroupClicked(event.yearGroup)
                is PopulationUiEvent.OnYearGroupDetailsDismissed -> viewModel.onYearGroupDetailsDismissed()
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

@Suppress("LongMethod", "CyclomaticComplexMethod")
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

    val xAxisModel = rememberIntLinearAxisModel(data.minOf(Point<Int, Int>::x)..data.maxOf(Point<Int, Int>::x))
    val yAxisModel = rememberIntLinearAxisModel(0..350000)

    val initialXViewRange = remember { xAxisModel.viewRange.value }
    val initialYViewRange = remember { yAxisModel.viewRange.value }

    val isZoomed by remember {
        derivedStateOf {
            xAxisModel.viewRange.value != initialXViewRange && yAxisModel.viewRange.value != initialYViewRange
        }
    }

    val xZoomRatio by remember {
        derivedStateOf {
            val initialWidth = initialXViewRange.endInclusive - initialXViewRange.start
            val currentWidth =
                xAxisModel.viewRange.value.endInclusive - xAxisModel.viewRange.value.start
            if (currentWidth > 0) initialWidth.toFloat() / currentWidth.toFloat() else 1.0f
        }
    }

    Surface(
        modifier = modifier,
    ) {
        ChartLayout(
            modifier = Modifier
                .padding(10.dp)
                .padding(end = yAxisLabelWidth + 4.dp),
        ) {
            XYGraph(
                xAxisModel = xAxisModel,
                yAxisModel = yAxisModel,
                xAxisStyle = rememberAxisStyle(
                    tickPosition = TickPosition.Inside,
                ),
                yAxisStyle = rememberAxisStyle(
                    tickPosition = TickPosition.Inside,
                ),
                horizontalMinorGridLineStyle = null,
                verticalMinorGridLineStyle = null,
                xAxisLabels = @Composable { value ->
                    Text(
                        text = "${value}年",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    )
                },
                yAxisLabels = @Composable { value ->
                    Text(
                        text = "${value / 10000}万",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
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
                zoomEnabled = true,
                panEnabled = true,
            ) {
                AreaPlot(
                    data = data,
                    lineStyle = LineStyle(
                        brush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant),
                        strokeWidth = 2.dp,
                    ),
                    areaStyle = AreaStyle(
                        brush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)),
                        alpha = 0.5f,
                    ),
                    areaBaseline = AreaBaseline.ConstantLine(0),
                    symbol = { point ->
                        if (!isZoomed) {
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
                                    yukawachoMergerNode =
                                        yukawachoMergerNode.copy(centerOffset = it)
                                    nodeOffsetList.add(it)
                                },
                                sendHakodateAirRaidOffset = {
                                    hakodateAirRaidNode =
                                        hakodateAirRaidNode.copy(centerOffset = it)
                                    nodeOffsetList.add(it)
                                },
                                sendToyamaruTyphoonOffset = {
                                    toyamaruTyphoonNode =
                                        toyamaruTyphoonNode.copy(centerOffset = it)
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
                        } else {
                            ZoomedYearSymbol(
                                yearGroup = uiState.yearGroups.find { it.year == point.x },
                                xZoomRatio = xZoomRatio,
                                onYearGroupClick = { yearGroup ->
                                    onEvent(PopulationUiEvent.OnYearGroupClicked(yearGroup))
                                },
                            )
                        }
                    },
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 36.dp)
                .padding(start = yAxisLabelWidth + 40.dp)
                .background(MaterialTheme.colorScheme.surface),
        ) {
            Text(
                text = "函館市の人口推移",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 4.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (!isZoomed) {
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
        }

        if (uiState.isCharacteristicNodeHovered) {
            uiState.characteristicNodeException?.let { exceptionList ->
                exceptionList.map {
                    Surface(
                        modifier = Modifier
                            .sizeIn(maxWidth = 400.dp)
                            .offset(x = it.offset.x.toDp(), y = it.offset.y.toDp()),
                        shape = MaterialTheme.shapes.medium,
                        shadowElevation = 5.dp,
                    ) {
                        Text(
                            text = it.exception,
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.labelMedium,
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

        if (uiState.isShowYearGroupDetails) {
            uiState.showingYearGroup?.let { yearGroup ->
                YearGroupDetailsView(
                    yearGroup = yearGroup,
                    onDismiss = { onEvent(PopulationUiEvent.OnYearGroupDetailsDismissed) },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Suppress("LongParameterList", "LongMethod")
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

@Suppress("LongParameterList")
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

@Suppress("MutableParams")
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
                margin = 5f,
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
                                characteristicWordNodeOffset,
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

// EventNodeがある年とそのEventTypeのマップ
private val eventNodeYears = mapOf(
    1907 to PopulationEventType.BigFire,
    1916 to PopulationEventType.BigFire,
    1934 to PopulationEventType.BigFire,
    1939 to PopulationEventType.Merger,
    1945 to PopulationEventType.AirRaid,
    1954 to PopulationEventType.Typhoon,
    1966 to PopulationEventType.Merger,
    1973 to PopulationEventType.Merger,
)

fun generateCharacteristicWordNodeOffset(
    origin: Offset,
    existingOffsets: List<Offset>,
    eventNodeSize: Float, // EventNode 正方形の一辺（px）
    margin: Float, // EventNode 周囲にほしい余白
    collisionMinDistance: Float,
    maxAttempts: Int = 100, // 1 リングあたりランダム試行回数
): Offset {
    val halfEvent = eventNodeSize / 2f
    var halfOuter = halfEvent + margin // まずは “ぴったり外側” から開始

    while (true) { // 成功するまで無限ループ
        repeat(maxAttempts) {
            // halfOuter 四角内でランダム生成
            val randomX = Random.nextDouble(-halfOuter.toDouble(), halfOuter.toDouble())
            val randomY = Random.nextDouble(-halfOuter.toDouble(), halfOuter.toDouble())
            val candidate = Offset(origin.x + randomX.toFloat(), origin.y + randomY.toFloat())

            // EventNode の正方形内部ならスキップ
            if (candidate.x in (origin.x - halfEvent)..(origin.x + halfEvent) &&
                candidate.y in (origin.y - halfEvent)..(origin.y + halfEvent)
            ) {
                return@repeat
            }

            // 既存ノードと衝突？
            val colliding = existingOffsets.any {
                distanceBetween(it, candidate) < collisionMinDistance
            }
            if (!colliding) return candidate // 衝突なし ⇒ 採用
        }

        // ここに来るのは “maxAttempts 失敗した” ときだけ
        halfOuter += margin // リングを 1 段拡張して再試行
    }
}

// ---------- LOD 設定 ----------
private const val Lod1Threshold = 1.6f // dot → 年＋代表語
private const val Lod2Threshold = 3.2f // 年＋全語
private const val MaxRepresentWord = 3 // LOD1 で表示する語数

@Suppress("LongMethod")
@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun ZoomedYearSymbol(
    yearGroup: YearGroup?,
    xZoomRatio: Float,
    modifier: Modifier = Modifier,
    onYearGroupClick: (YearGroup) -> Unit = {},
) {
    val lod by remember(xZoomRatio) {
        derivedStateOf {
            when {
                xZoomRatio < Lod1Threshold -> 0
                Lod1Threshold < xZoomRatio && xZoomRatio < Lod2Threshold -> 1
                else -> 2
            }
        }
    }

    // EventNodeがある年かどうかを判定し、EventColorを取得
    val eventColor = yearGroup?.year?.let { year ->
        eventNodeYears[year]?.let { eventType ->
            getEventColor(eventType)
        }
    }

    val density = LocalDensity.current
    val isEvenYear = yearGroup?.year?.rem(2) == 1
    val leaderLineHeight = 40.dp
    val symbolSize = 8.dp

    var yearInfoBoxHeight by remember { mutableStateOf(50.dp) }

    // 色を決定（EventNodeがある年は専用色、ない年はデフォルト色）
    val dividerColor = eventColor?.base ?: MaterialTheme.colorScheme.onSurfaceVariant
    val symbolColor = eventColor?.emphasis ?: MaterialTheme.colorScheme.onSurfaceVariant

    Column(
        modifier = modifier
            .offset(
                y = if (isEvenYear) {
                    // 上側配置: グラフ頂点から上方向にオフセット
                    -(symbolSize + leaderLineHeight + yearInfoBoxHeight) / 2
                } else {
                    // 下側配置: グラフ頂点から下方向にオフセット
                    (symbolSize + leaderLineHeight + yearInfoBoxHeight) / 2
                },
            ),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        if (isEvenYear) {
            // 上側配置: YearInfo → Divider → Symbol (グラフ頂点に近い順)
            YearInfoBox(
                yearGroup = yearGroup,
                lod = lod,
                eventColor = eventColor,
                onHeightMeasure = { height ->
                    yearInfoBoxHeight = with(density) { height.toDp() }
                },
                onYearGroupClick = onYearGroupClick,
            )
            VerticalDivider(
                modifier = Modifier.height(leaderLineHeight),
                thickness = 2.dp,
                color = dividerColor,
            )
            Symbol(
                shape = CircleShape,
                size = symbolSize,
                fillBrush = SolidColor(symbolColor),
            )
        } else {
            // 下側配置: Symbol → Divider → YearInfo (グラフ頂点に近い順)
            Symbol(
                shape = CircleShape,
                size = symbolSize,
                fillBrush = SolidColor(symbolColor),
            )
            VerticalDivider(
                modifier = Modifier.height(leaderLineHeight),
                thickness = 2.dp,
                color = dividerColor,
            )
            YearInfoBox(
                yearGroup = yearGroup,
                lod = lod,
                eventColor = eventColor,
                onHeightMeasure = { height ->
                    yearInfoBoxHeight = with(density) { height.toDp() }
                },
                onYearGroupClick = onYearGroupClick,
            )
        }
    }
}

@Composable
private fun YearInfoBox(
    yearGroup: YearGroup?,
    lod: Int,
    modifier: Modifier = Modifier,
    eventColor: EventColor? = null,
    onHeightMeasure: (Int) -> Unit = {},
    onYearGroupClick: (YearGroup) -> Unit = {},
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp),
                )
                .border(
                    width = 1.dp,
                    color = eventColor?.emphasis ?: MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(8.dp),
                )
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    yearGroup?.let { onYearGroupClick(it) }
                }
                .padding(4.dp)
                .animateContentSize()
                .onGloballyPositioned { layoutCoordinates ->
                    onHeightMeasure(layoutCoordinates.size.height)
                },
        ) {
            yearGroup?.let {
                Text(
                    text = it.year.toString() + "年",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(4.dp))

                when (lod) {
                    1 -> {
                        // LOD1: 年＋代表語
                        it.characteristicWords.take(MaxRepresentWord).forEach { word ->
                            Text(
                                text = word,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }

                    2 -> {
                        // LOD2: 年＋全語
                        it.characteristicWords.forEach { word ->
                            Text(
                                text = word,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun distanceBetween(offset1: Offset, offset2: Offset): Float {
    val dx = offset2.x - offset1.x
    val dy = offset2.y - offset1.y
    return sqrt(dx * dx + dy * dy)
}
