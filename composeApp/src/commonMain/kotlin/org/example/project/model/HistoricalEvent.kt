package org.example.project.model

import kotlinx.serialization.Serializable

@Serializable
data class HistoricalEvent(
    val id: Int,
    val jyear: String,
    val year: Int,
    val leapMonth: Int,
    val month: Int?,
    val day: Int?,
    val season: String?,
    val eto: String,
    val text: String,
    val source1: String?,
    val source2: String?,
    val source3: String?,
    val source4: String?,
    val source5: String?,
    val source6: String?,
)