package org.example.project.ui.base

import androidx.compose.runtime.compositionLocalOf
import io.github.koalaplot.core.xygraph.Point

val LocalData = compositionLocalOf<List<Point<Int, Int>>> { emptyList() }
