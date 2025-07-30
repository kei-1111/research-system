package org.example.project.di

import org.example.project.data.historicalevent.HistoricalEventsRepository
import org.example.project.ui.feature.population.PopulationViewModel
import org.koin.dsl.module

val populationModule = module {
    single { HistoricalEventsRepository() }
    factory { PopulationViewModel(get()) }
}
