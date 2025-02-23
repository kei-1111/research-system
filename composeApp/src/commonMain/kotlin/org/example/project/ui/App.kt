package org.example.project.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.project.ui.feature.population.PopulationScreen

@Suppress("ModifierMissing")
@Composable
fun App() {
    MaterialTheme {
        PopulationScreen()
    }
}
