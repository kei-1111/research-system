package org.example.project.data

import org.example.project.model.MajorEvent
import research_system.composeapp.generated.resources.Res
import research_system.composeapp.generated.resources.meiji40_big_fire_thumbnail

val meiji40BigFire = MajorEvent(
    gregorianCalender = 1907,
    japaneseCalendar = "明治40年",
    name = "明治40年大火",
    thumbnailImage = Res.drawable.meiji40_big_fire_thumbnail,
    characteristicWordList = listOf(
        "明治40年",
        "大火",
        "水源\n枯渇",
        "密集\n家屋",
        "8977戸\n焼失",
        "風速\n16メートル",
        "阿鼻\n叫喚",
        "函館港\n全滅",
    )
)