package org.example.project.ui

import androidx.compose.runtime.Composable
import org.example.project.ui.feature.population.PopulationScreen
import org.example.project.ui.theme.ResearchSystemTheme

@Suppress("ModifierMissing")
@Composable
fun App() {
    ResearchSystemTheme {
        PopulationScreen()
    }
}
