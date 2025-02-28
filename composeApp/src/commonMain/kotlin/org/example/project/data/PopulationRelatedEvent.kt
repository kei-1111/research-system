package org.example.project.data

import org.example.project.model.MajorEvent
import research_system.composeapp.generated.resources.Res
import research_system.composeapp.generated.resources.meiji40_big_fire_thumbnail

val meiji40BigFire = MajorEvent(
    gregorianCalender = 1907,
    japaneseCalendar = "明治40年",
    name = "明治40年大火",
    thumbnailImage = Res.drawable.meiji40_big_fire_thumbnail,
    characteristicWordMap = mapOf(
        "明治40年" to "明治40年8月25日午後10時20分東川町217番地より出火した",
        "大火" to "負傷者1000名を出してこの大火は翌26日午前9時頃にやっと鎮火した（『函館大火史』）",
        "水源\n枯渇" to "不幸にも水源が枯渇し日々の用水にも欠乏していたため消火栓の効力が少なく",
        "密集\n家屋" to "家屋が租造のうえ密集していたため四方に延焼し焼失区域を拡大していった",
        "8977戸\n焼失" to "33か町8977戸（区役所調べ1万2390戸）が焼失し",
        "風速\n16メートル" to "翌26日午前1時頃には16メートルの暴風となった",
        "阿鼻\n叫喚" to "一瞬時にして忽ち阿鼻叫喚の焦熱地獄を演出して",
        "函館港\n全滅" to "時間から云へば僅かに三四時間ならぬ間に日本五港の一たる函館港を全滅に近きまで焼き盡した",
    ),
)
