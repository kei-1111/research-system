

package org.example.project.data.historicalevent

import kotlinx.serialization.SerializationException
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
        } catch (e: SerializationException) {
            println("ERROR: JSON parsing failed - $e")
            Result.failure(e)
        } catch (e: IllegalArgumentException) {
            println("ERROR: Invalid resource path - $e")
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
