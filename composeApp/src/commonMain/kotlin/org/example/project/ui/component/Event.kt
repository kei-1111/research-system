package org.example.project.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.Symbol
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import org.example.project.model.MajorEvent

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun Event(
    event: MajorEvent,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
//            yの値は、EventNodeの高さ（100.dp）と、引出線の長さ（50.dp）と、Symbolの高さ（10.dp）をすべて足し合わせて、2で割り、Symbolをグラフの線に合わせるために5.dpを引いた値
//            ↑のようにした背景として、Symbolとして使用するものは、要素を中央揃えにされているからである。
            .offset(y = 75.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Symbol(
            shape = MaterialTheme.shapes.small,
            size = 10.dp,
            fillBrush = SolidColor(Color.Black)
        )
        VerticalDivider(
            modifier = Modifier.height(50.dp),
            thickness = 4.dp,
        )
        ConnectNodeWithLine(
            event = event,
        )
    }
}
