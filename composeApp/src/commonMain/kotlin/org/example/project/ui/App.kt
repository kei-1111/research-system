package org.example.project.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.line.AreaBaseline
import io.github.koalaplot.core.line.AreaPlot
import io.github.koalaplot.core.style.AreaStyle
import io.github.koalaplot.core.style.LineStyle
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.xygraph.IntLinearAxisModel
import io.github.koalaplot.core.xygraph.Point
import io.github.koalaplot.core.xygraph.XYGraph
import org.example.project.data.totalPopulation

@Suppress("ModifierMissing")
@Composable
fun App() {
    MaterialTheme {
        GraphSample()
    }
}

@Suppress("MagicNumber")
@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun GraphSample(
    modifier: Modifier = Modifier,
) {
    ChartLayout(
        modifier = modifier,
        title = { Text("Graph Sample") },
    ) {
        XYGraph(
            xAxisModel = IntLinearAxisModel(totalPopulation.indices),
            yAxisModel = IntLinearAxisModel(0..350000),
            horizontalMinorGridLineStyle = null,
            verticalMinorGridLineStyle = null,
            zoomEnabled = true,
        ) {
            AreaPlot(
                data = totalPopulation.toPointList(),
                lineStyle = LineStyle(brush = SolidColor(Color(0xFF37A78F)), strokeWidth = 2.dp),
                areaStyle = AreaStyle(
                    brush = SolidColor(Color(0xFF37A78F)),
                    alpha = 0.5f,
                ),
                areaBaseline = AreaBaseline.ConstantLine(0),
            )
        }
    }
}

private fun Collection<Int>.toPointList(): List<Point<Int, Int>> {
    return this.mapIndexed { index, e -> Point(index, e) }
}
