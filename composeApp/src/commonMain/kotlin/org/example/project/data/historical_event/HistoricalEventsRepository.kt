package org.example.project.data.historical_event

import kotlinx.serialization.json.Json
import org.example.project.model.YearGroup
import org.jetbrains.compose.resources.ExperimentalResourceApi
import research_system.composeapp.generated.resources.Res

class HistoricalEventsRepository {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private var cachedYearGroups: List<YearGroup>? = null

    @OptIn(ExperimentalResourceApi::class)
    private suspend fun loadYearGroupsFromJson(): Result<List<YearGroup>> {
        return try {
            val jsonString = Res.readBytes("files/historical_events.json").decodeToString()
            val yearGroups = json.decodeFromString<List<YearGroup>>(jsonString)
            Result.success(yearGroups)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getYearGroups(): Result<List<YearGroup>> {
        return if (cachedYearGroups != null) {
            Result.success(cachedYearGroups!!)
        } else {
            loadYearGroupsFromJson().also { result ->
                result.onSuccess { yearGroups ->
                    cachedYearGroups = yearGroups
                }
            }
        }
    }
}
