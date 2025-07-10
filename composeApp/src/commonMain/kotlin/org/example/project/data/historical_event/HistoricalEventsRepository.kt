package org.example.project.data.historical_event

import kotlinx.serialization.json.Json
import org.example.project.model.HistoricalEvent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import research_system.composeapp.generated.resources.Res

class HistoricalEventsRepository {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private var cachedEvents: List<HistoricalEvent>? = null

    @OptIn(ExperimentalResourceApi::class)
    private suspend fun loadEventsFromJson(): Result<List<HistoricalEvent>> {
        return try {
            val jsonString = Res.readBytes("files/historical_events.json").decodeToString()
            val events = json.decodeFromString<List<HistoricalEvent>>(jsonString)
            Result.success(events)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getHistoricalEvents(): Result<List<HistoricalEvent>> {
        return if (cachedEvents != null) {
            Result.success(cachedEvents!!)
        } else {
            loadEventsFromJson().also { result ->
                result.onSuccess { events ->
                    cachedEvents = events
                }
            }
        }
    }

    suspend fun getHistoricalEventById(id: Int): Result<HistoricalEvent?> {
        return getHistoricalEvents().map { events ->
            events.find { it.id == id }
        }
    }

    suspend fun getHistoricalEventsByYear(year: Int): Result<List<HistoricalEvent>> {
        return getHistoricalEvents().map { events ->
            events.filter { it.year == year }
        }
    }

    suspend fun getHistoricalEventsByYearRange(startYear: Int, endYear: Int): Result<List<HistoricalEvent>> {
        return getHistoricalEvents().map { events ->
            events.filter { it.year in startYear..endYear }
        }
    }

    suspend fun searchHistoricalEventsByText(query: String): Result<List<HistoricalEvent>> {
        return getHistoricalEvents().map { events ->
            events.filter { it.text.contains(query, ignoreCase = true) }
        }
    }
}
