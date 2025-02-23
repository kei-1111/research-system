package org.example.project.utils

import io.github.koalaplot.core.xygraph.Point

fun Collection<Int>.toPointList(baseYear: Int): List<Point<Int, Int>> {
    return this.mapIndexed { index, e -> Point(baseYear + index, e) }
}