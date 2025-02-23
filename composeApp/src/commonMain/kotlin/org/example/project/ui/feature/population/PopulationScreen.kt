@file:Suppress("MagicNumber")

package org.example.project.ui.feature.population

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.Symbol
import io.github.koalaplot.core.line.AreaBaseline
import io.github.koalaplot.core.line.AreaPlot
import io.github.koalaplot.core.style.AreaStyle
import io.github.koalaplot.core.style.LineStyle
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.xygraph.IntLinearAxisModel
import io.github.koalaplot.core.xygraph.XYGraph
import org.example.project.data.meiji40BigFire
import org.example.project.data.totalPopulation
import org.example.project.ui.component.Event
import org.example.project.ui.component.TitleMediumText
import org.example.project.ui.theme.dimensions.Paddings
import org.example.project.utils.toPointList

@OptIn(ExperimentalKoalaPlotApi::class)
@Suppress("ModifierMissing")
@Composable
fun PopulationScreen() {
    val data = totalPopulation.toPointList(Meiji5)

    ChartLayout(
        modifier = Modifier
            .padding(Paddings.Small),
        title = { TitleMediumText("歴史的出来事可視化システム") }
    ) {
        XYGraph(
            xAxisModel = IntLinearAxisModel(data.minOf{it.x}..data.maxOf{it.x}),
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
                        )
                    } else {
                        Symbol(
                            modifier = Modifier,
                        )
                    }
                }
            )
        }
    }
}

private const val Meiji5 = 1872
