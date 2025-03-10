package org.example.project.data

import kotlinx.collections.immutable.persistentMapOf
import org.example.project.model.MajorEvent
import org.example.project.model.PopulationEventType
import org.example.project.model.PopulationRelatedEvent
import research_system.composeapp.generated.resources.Res
import research_system.composeapp.generated.resources.hakodate_air_raid_photo_image1
import research_system.composeapp.generated.resources.hakodate_air_raid_thumbnail
import research_system.composeapp.generated.resources.kamedasi_merger_photo_image1
import research_system.composeapp.generated.resources.kamedasi_merger_thumbnail
import research_system.composeapp.generated.resources.meiji40_big_fire_photo_image1
import research_system.composeapp.generated.resources.meiji40_big_fire_photo_image2
import research_system.composeapp.generated.resources.meiji40_big_fire_photo_image3
import research_system.composeapp.generated.resources.meiji40_big_fire_postcard_image1
import research_system.composeapp.generated.resources.meiji40_big_fire_postcard_image2
import research_system.composeapp.generated.resources.meiji40_big_fire_thumbnail
import research_system.composeapp.generated.resources.syowa9_big_fire_map_image1
import research_system.composeapp.generated.resources.syowa9_big_fire_map_image2
import research_system.composeapp.generated.resources.syowa9_big_fire_photo_image1
import research_system.composeapp.generated.resources.syowa9_big_fire_photo_image2
import research_system.composeapp.generated.resources.syowa9_big_fire_photo_image3
import research_system.composeapp.generated.resources.syowa9_big_fire_postcard_image1
import research_system.composeapp.generated.resources.syowa9_big_fire_postcard_image2
import research_system.composeapp.generated.resources.syowa9_big_fire_thumbnail
import research_system.composeapp.generated.resources.taisyo5_big_fire_postcard_image1
import research_system.composeapp.generated.resources.taisyo5_big_fire_postcard_image2
import research_system.composeapp.generated.resources.taisyo5_big_fire_postcard_image3
import research_system.composeapp.generated.resources.taisyo5_big_fire_postcard_image4
import research_system.composeapp.generated.resources.taisyo5_big_fire_postcard_image5
import research_system.composeapp.generated.resources.taisyo5_big_fire_postcard_image6
import research_system.composeapp.generated.resources.taisyo5_big_fire_postcard_image7
import research_system.composeapp.generated.resources.taisyo5_big_fire_thumbnail
import research_system.composeapp.generated.resources.toyamaru_typhoon_photo_image1
import research_system.composeapp.generated.resources.toyamaru_typhoon_postcard_image1
import research_system.composeapp.generated.resources.toyamaru_typhoon_postcard_image2
import research_system.composeapp.generated.resources.toyamaru_typhoon_thumbnail
import research_system.composeapp.generated.resources.yukawacho_merger_photo_image1
import research_system.composeapp.generated.resources.yukawacho_merger_thumbnail
import research_system.composeapp.generated.resources.zenikamesawamura_merger_photo_image1
import research_system.composeapp.generated.resources.zenikamesawamura_merger_thumbnail

val meiji40BigFire = MajorEvent(
    gregorianCalender = 1907,
    japaneseCalendar = "明治40年",
    eventType = PopulationEventType.BigFire,
    id = PopulationRelatedEvent.Meiji40BigFire,
    name = "明治40年大火",
    exception = """
            明治40年8月25日午後10時20分東川町217番地より出火した。出火時は南東の風で風速10メートルの強風であったが、しだいに風力が強くなり、翌26日午前1時頃には16メートルの暴風となった。しかも出火場所付近は、家屋が租造のうえ密集していたため四方に延焼し焼失区域を拡大していった。さらにこの時期は、不幸にも水源が枯渇し日々の用水にも欠乏していたため消火栓の効力が少なく、警察官や消防組員等が鋭意防御に尽力しても容易に消化活動がすすまなかったのである。33か町8977戸（区役所調べ1万2390戸）が焼失し、その損害は実に3114万8337円にのぼった。焼死者8名、負傷者1000名を出してこの大火は翌26日午前9時頃にやっと鎮火した（『函館大火史』）。
            この大火の様子を当時の新聞は、「丁度火事の當日は畜産共進會やら競馬やらで函館全市近来無比の大賑わひの最中で、中にも競馬に關係ある函館資産家の多数は勝祝ひやら負祝ひやらで各料理店とも笛や太鼓の大陽氣な眞最中、ソレ火事だといふが早いか今の時まで臥牛山下の平穏無事な陽氣な天地は、一瞬時にして忽ち阿鼻叫喚の焦熱地獄を演出して、時間から云へば僅かに三四時間ならぬ間に日本五港の一たる函館港を全滅に近きまで焼き盡した。今度の火事は實に安政の地震の時に江戸市中を燒き沸つたといふ江戸大火以來の大火かと思われます」（明治40年8月30日付「北タイ」）と伝えている。
    """.trimIndent().replace(Regex("(?m)^"), "　"),
    thumbnailImage = Res.drawable.meiji40_big_fire_thumbnail,
    mapImages = null,
    photoImages = persistentMapOf(
        Res.drawable.meiji40_big_fire_photo_image1 to "https://archives.c.fun.ac.jp/photos/gl000123/0001",
        Res.drawable.meiji40_big_fire_photo_image2 to "https://archives.c.fun.ac.jp/photos/gl000124/0001",
        Res.drawable.meiji40_big_fire_photo_image3 to "https://archives.c.fun.ac.jp/photos/gl000124/0001",
    ),
    postcardImages = persistentMapOf(
        Res.drawable.meiji40_big_fire_postcard_image1 to "https://archives.c.fun.ac.jp/postcards/pc000762/0001",
        Res.drawable.meiji40_big_fire_postcard_image2 to "https://archives.c.fun.ac.jp/postcards/pc000762/0002",
    ),
)

val taisyo5BigFire = MajorEvent(
    gregorianCalender = 1916,
    japaneseCalendar = "大正5年",
    eventType = PopulationEventType.BigFire,
    id = PopulationRelatedEvent.Taisyo5BigFire,
    name = "大正5年大火",
    exception = """
            旭町40番地の白玉製造所から出火し、水道断水中もあって942棟1763戸を焼失する。
            当時は炎天続きで家屋の乾燥が甚だしく、4時間後にようやく鎮火し10年ぶりの大火となる。
            電車の十字街・地蔵町・鶴岡町・停車場前・大門前間と十字街・鶴岡町・真砂町・停車場前・若松橋間は5日まで運転が休止される
    """.trimIndent().replace(Regex("(?m)^"), "　"),
    thumbnailImage = Res.drawable.taisyo5_big_fire_thumbnail,
    mapImages = null,
    photoImages = null,
    postcardImages = persistentMapOf(
        Res.drawable.taisyo5_big_fire_postcard_image1 to "https://archives.c.fun.ac.jp/postcards/pc000759/0001",
        Res.drawable.taisyo5_big_fire_postcard_image2 to "https://archives.c.fun.ac.jp/postcards/pc000759/0002",
        Res.drawable.taisyo5_big_fire_postcard_image3 to "https://archives.c.fun.ac.jp/postcards/pc000759/0003",
        Res.drawable.taisyo5_big_fire_postcard_image4 to "https://archives.c.fun.ac.jp/postcards/pc000759/0004",
        Res.drawable.taisyo5_big_fire_postcard_image5 to "https://archives.c.fun.ac.jp/postcards/pc000759/0005",
        Res.drawable.taisyo5_big_fire_postcard_image6 to "https://archives.c.fun.ac.jp/postcards/pc000759/0006",
        Res.drawable.taisyo5_big_fire_postcard_image7 to "https://archives.c.fun.ac.jp/postcards/pc000759/0007",
    ),
)

val syowa9BigFire = MajorEvent(
    gregorianCalender = 1934,
    japaneseCalendar = "昭和9年",
    eventType = PopulationEventType.BigFire,
    id = PopulationRelatedEvent.Syowa9BigFire,
    name = "昭和9年大火",
    exception = """
            昭和9年3月21日午後6時53分、函館市住吉町の民家より発火した火災は、風速20余メートルにおよぶ東南の烈風に煽られて火勢劇烈を極め、瞬時にして他に延焼拡大していった。物凄い火炎は、青柳町より豊川町、鶴岡町、松風町、新川町方面を襲い、消防隊や軍隊等の必死の努力も、烈風と倒壊した家屋や電柱等の障害物に妨げられ、充分なる機能を発揮することが出来なかったのである。その後、風向が漸次西方に変化するに伴って末広町、会所町、元町方面は幸いに二十間坂によって延焼を遮断することが出来たが、反対に風下にあたる新川町、堀川町、的場町の間は全く廃墟に帰し、さらに北方に向かって延焼し、翌22日午前6時頃に鎮火することができたのである。実に、全市の3分の1を焼失したのである（『函館大火災害史』）。
            大火の被害が大きかった原因を、当時の建築学会による報告書は、「一．発火より消防署に於て知覚するまでに約五分を要したこと、二．風力甚大で火災の伝播速度大且飛火多く尚風向の旋転方向亦最悪的であった事、三．火元付近は特に地形の関係に依り延焼中頻りに風の旋転、突風起りし事、四．発火地点及海岸付近は特に矮小粗悪木造家屋連担し且全市に亘り粗雑木造家屋が多かった事、五．防火地区極めて尠く、広場、公園等の都市計画上の施設が完備して居なかった事、六．発火地点は水道終点である為め水圧弱く水量乏しく、加ふるに風力強き為めに消防組の活動意の如く行われなかった事、七．道路概して狭隘にして消防組の部署変更に困難なりし事」（『函館大火災（昭和9年3月21日）調査報告』）と説明しており都市計画事業との関連性を想起させている。
    """.trimIndent().replace(Regex("(?m)^"), "　"),
    thumbnailImage = Res.drawable.syowa9_big_fire_thumbnail,
    mapImages = persistentMapOf(
        Res.drawable.syowa9_big_fire_map_image1 to "https://archives.c.fun.ac.jp/documents/1810470672/0001",
        Res.drawable.syowa9_big_fire_map_image2 to "https://archives.c.fun.ac.jp/documents/1810575397/0001",
    ),
    photoImages = persistentMapOf(
        Res.drawable.syowa9_big_fire_photo_image1 to "https://archives.c.fun.ac.jp/photos/gl000144/0001",
        Res.drawable.syowa9_big_fire_photo_image2 to "https://archives.c.fun.ac.jp/photos/gl000140/0001",
        Res.drawable.syowa9_big_fire_photo_image3 to "https://archives.c.fun.ac.jp/photos/gl000150/0001",
    ),
    postcardImages = persistentMapOf(
        Res.drawable.syowa9_big_fire_postcard_image1 to "https://archives.c.fun.ac.jp/postcards/pc001036/0001",
        Res.drawable.syowa9_big_fire_postcard_image2 to "https://archives.c.fun.ac.jp/postcards/pc001033/0002",

    ),
)

val yukawachoMerger = MajorEvent(
    gregorianCalender = 1939,
    japaneseCalendar = "昭和14年",
    eventType = PopulationEventType.Merger,
    id = PopulationRelatedEvent.YukawachoMerger,
    name = "湯川町合併",
    exception = """
            合併調査開始以来紆余曲折を経て、昭和13年8月に入って湯川町から合併条件が提示された。さきの促進事項2点のほか、湯川小学校の移転改築、租税賦課を現在のままで5年間増額しないこと、道路の改修舗装、湯川町農会を函館市農会とすることなど17項目が提示された。市側の合併委員会での協議の結果、5項目を合併条件、10項目を希望条件とすることと決定した。
            その後、湯川側と交渉の結果全てを希望条件とすることで一致し、10月5日、函館市会には建議案の形式で上程され、湯川町会には諮問案の形式で上程され、両会とも満場異議なく決定したのである。いよいよ本格的合併工作に入ることとなった。最終的な合併希望条件は23項目で、さきに提示された合併条件がほぼ希望条件の骨格で、「各事項は函館市においてこれを容認し市の発展と財政の許す範囲において可及的速に実現努力すること」という一文が付されていた。
            なお、函館市会での建議案上程理由は次の通りであった（昭和13年10月6日付「函日」）。
            輓近東部方面の伸長著しく郊外に居を占むる者一層多きを加へ隣接湯川町は殆ど市と利害休戚を一にするの実情にあり之を同一の機関の下に統括し共存共栄の実績を挙ぐるは刻下焦眉の急務なり、而も同町は市の都市計画区域に属し交通至便にして総て本市を中心に経済的機構を営み人情風俗は勿論実生活に於ても何等異なる処なき現象なるに行政区域を分ち諸般の施設を為すは得策ならず、殊に早晩人家隣接するの趨勢に鑑み渾然一体となり相互に連絡統一ある施設を為し温泉地として将来観光地として諸設備を完成するは彼我の利益なりと認むるに因る
            
            その後、合併に関する上申書が双方から道庁に提出され、合併への手続きも順調に進み、昭和14年3月6日付けで合併に関する道庁諮問（函館市境界変更及び変更に伴う湯の川町有財産処分）が発せられ、函館市会は8日同諮問に対して異議なき旨の答申を満場一致で可決した。ところが湯川町会は時期尚早論者が大勢を占め紛糾した。5年間税金は現状維持とする希望条件が容れられず、漸増とすることで決着を見たため、1銭でも増税は認められないという空気が広がり、合併を時期尚早とする意見が大勢となったのである。
            このため道庁の地方課長が来町、町会議員を説得する場面も出現した。今日の機会を失しては将来到底合併は不可能と現実論を説いたわけである。翌10日、湯川町では拡大委員会が開催され、続いて町会が道庁の諮問を異議なく可決した（3月11日付「函新」）。
    """.trimIndent().replace(Regex("(?m)^"), "　"),
    thumbnailImage = Res.drawable.yukawacho_merger_thumbnail,
    mapImages = null,
    photoImages = persistentMapOf(
        Res.drawable.yukawacho_merger_photo_image1 to "https://archives.c.fun.ac.jp/photos/ph900026/0023",
    ),
    postcardImages = null,
)

val hakodateAirRaid = MajorEvent(
    gregorianCalender = 1945,
    japaneseCalendar = "昭和20年",
    eventType = PopulationEventType.AirRaid,
    id = PopulationRelatedEvent.HakodateAirRaid,
    name = "函館空襲",
    exception = """
        太平洋戦争の終結もま近い昭和20年7月14、15日の両日、東北・北海道地方はアメリカ海軍機動部隊の攻撃を受け、本州と北海道を結ぶ青函連絡船の基地でもある函館市は大きな被害を受けた。
        昭和20年に入って次第に激しくなるアメリカ軍の日本空襲には、北海道と本州以南とで決定的に異なっている点がある。それは、後者の青森を北限とする本州・四国・九州方面の空襲が、もっぱらマリアナ諸島を基地とするアメリカ空軍のB29爆撃機によって行われたのに対し、同機の航続距離の関係もあって、7月中旬の北海道空襲は、青森県沖の太平洋上に進航したアメリカ海軍の艦載機によって行われたことである。ただし、同20年5月下旬以降、B29による北海道方面の偵察飛行はしばしば行われた。
        いま、『青柳国民学校日誌』（『地域史研究はこだて』第10号所収）により、同年5月末以降の警戒警報発令状況をみると、次のようになっている。
        
        六月二十七日　午前十一時過突如空襲警報発令
            函館上空ヨリ札幌室蘭地区偵察後釧路帯広地区、他ノ一機ハ海上ヨリ室蘭内浦湾上空津軽海峡等偵察午后一時過脱去午后二時過全域ノ警戒警報解除サル
        六月二十八日　警戒警報
            十二時四十五分頃発令十三時五十分頃解除サレタリ
            敵大型機壱機津軽海面ヨリ室蘭地区等偵察
        六月二十九日　警報発令
            正午頃警戒警報発令続イテ空襲警報トナル　児童ハ警戒警報ニテ全部直チニ帰宅セシメタリ　函館地区通過室蘭上空ニテ同高射砲隊及友軍機ニテ之ヲ攻撃敵機ハ黒煙ヲ吐キツゝ逃走
            午后一時五十分頃警戒警報解除サレタリ
        
        翌6月30日にも、正午に警戒警報が発令され、「敵大型二機」が倶知安地区・室蘭地区の偵察を行ったとある。
        7月に入ると、2日午前に「津軽海面警戒警報」が発令され、「敵大型二機大湊上空旋回中」との防空情報が入った。同3日の日誌に、「夜直　午前一時十五分（四日）半鐘点打ニ引続キ警戒警報発令　敵二機一目標ハ函館ヨリ札幌室蘭反転札幌ニ至リ再ビ室蘭ヲ経テ脱去他ハ恵山ヨリ室蘭札幌反転内浦湾上ヲ旋回シテ脱去其ノ間空襲警報待避合図等アリ警報解除ハ午前二時五十七分ナリキ」と記されている。なお日誌には、「爾後ハ敵、友軍機ヲ間ハズ爆音ヲ耳ニシタル場［合（脱カ）］ハ警鐘ヲ点打スルコトトナリタル由」との付記がある。
        その後、7月9日午前10時15分にも警戒警報が発令され、B29、1機が「倶知安地区ヨリ東北進」した。同夜午後11時6分、再び北海道地区に警戒警報が発令され、「敵大型二機室蘭南方海面ヨリ室蘭地区ニ侵入北進倶知安地区ニ至リ更ニ札幌ヲ経テ小樽湾上空ヲ旋回再ビ札幌地区北東部ヲ経テ深川、旭川ヲ偵察、反轉三度札幌地区ニ侵入、更ニ小樽湾倶知安地区ヲ南下内浦湾上空ヲ南下シテ午前一時十五分東南方洋上ニ脱去」した。
        以上みてきたように、6月末以降7月上旬にかけて米軍機による北海道方面の偵察活動は活発を極めたが、これらの動きは、来るべき北海道方面への空襲の予兆であることを函館市民は感じていたのである。これより先7月1日、フィリピンのレイテ湾を出港したアメリカ海軍の機動部隊は、7月10日に関東地区を攻撃した後さらに北上を続け、7月13日には青森県尻屋岬の南東約100海里の太平洋上に設定された発進海域に到着した（以下、防衛庁防衛研修所戦史室『戦史叢書　本土方面海軍作戦』昭和56年、青函連絡船戦災史編集委員会『白い航跡』1995年による）。
        アメリカ海軍の機動部隊は3グループで編成され、エセックス級大型空母やインデペンデンス級軽空母など合計13隻の空母が配備されていた。そして、第三八・一任務群は、北海道東部の飛行場と船舶攻撃および艦砲射撃部隊の支援、第三八・三任務群は、東北北部の飛行場と津軽海峡内の船舶攻撃、第三八・四任務群には北海道南部の飛行場と船舶攻撃という目標が与えられていた。
        7月14日早朝、太平洋上の空母から発進したアメリカ軍の艦載機約2000は、各々の目標に向かって一斉に攻撃を開始した。その状況を、前記の『青柳国民学校日誌』は13日の当直欄に次のように記している。
        
        午後十一時五十分東北地区空襲警報発令トノ連絡
            徹宵警戒中ナルモ異常ナク十四日午前四時五十分頃大湊情報聴取直チニ全員配置ニツク
        五時十八分頃敵艦上戦闘機当市上空ニ出現校舎銃撃セラレシモ異常ナシ
        
        午前中の攻撃に続いて、午後1時43分頃にも警戒警報、さらに空襲警報が発令され、3時15分頃には「奉安殿側非常口壁に機銃弾を一発受く屋上にも弾痕らしきものあり」、4時20分ようやく空襲警報が解除された。
        また、市立函館図書館の『市立函館図書館日誌』の7月14日の条には、「空襲警報　午前五時頃発令仝四十分頃遂に本市に侵入せる敵機（小型）は機銃掃射を以て攻撃し来れり、被害民族館東側硝子窓一枚及陳列欄硝子一枚破壊、天井に二箇處の弾痕を認む。本館異状なし。警戒警報解除午后五時」と記されている。
    """.trimIndent().replace(Regex("(?m)^"), "　"),
    thumbnailImage = Res.drawable.hakodate_air_raid_thumbnail,
    mapImages = null,
    photoImages = persistentMapOf(
        Res.drawable.hakodate_air_raid_photo_image1 to "https://archives.c.fun.ac.jp/photos/ph003189/0001",
    ),
    postcardImages = null,
)

val toyamaruTyphoon = MajorEvent(
    gregorianCalender = 1954,
    japaneseCalendar = "昭和29年",
    eventType = PopulationEventType.Typhoon,
    id = PopulationRelatedEvent.ToyamaruTyphoon,
    name = "洞爺丸台風",
    exception = """
            津軽海峡の大動脈として活躍した青函連絡船が、明治41（1908）年3月に就航してから、昭和63（1988）年3月に終航するまでの80年間にわたる長い歴史にあって、最大の悲劇は、昭和29（1954）年9月26日、函館を襲った台風15号による洞爺丸、第十一青函丸、北見丸、十勝丸、日高丸の5隻もの青函連絡船の転覆沈没事故である。
            大正元（1912）年4月、北大西洋で氷山に激突して沈没したイギリスの豪華客船タイタニック号（死者1517名）に次ぐこの海難事故は、乗客・乗員あわせて1430名（洞爺丸は1155名）の尊い人命がうばわれ、生存者はわずか202名（同159名）、そして112名（同37名）の遺体は、今なお発見されていない（青函船舶鉄道管理局『航跡－青函連絡船七〇年のあゆみ』）。
            北海道は、食糧、石炭の供給基地として重要な役割を担ってきたが、昭和20年7月、青函連絡船がアメリカ軍の空襲で壊滅的な打撃を受けたことにより、本州方面への物資の輸送も寸断されることになった。
            同年8月15日、敗戦を迎えたときには、日本の輸送機能のすべてが一時停止したとさえいわれるが、国有鉄道の輸送力を確保することは、戦後日本が国が復興するための原動力となるものであり、青函連絡船の回復は、国家的要請となった。そのため昭和21年には、GHQも8隻の貨車航送船の造船を許可したのである。このなかの1隻が洞爺丸で、就航は昭和22年11月のことであった。
            台風15号は、洞爺丸台風ともいわれるが、それは洞爺丸が、3898総トン、旅客定員932名、積載貨車18両の性能を有する豪華客船の第1船として建造されたことと、青森・函館間を4時間半で走航したという名実共に、津軽海峡の女王として君臨したシンボル船の海難事故であったからにほかならない。
            カロリン諸島東部海上に発生した熱帯低気圧は北上を続け、悲劇を迎える9月26日には、時速110キロメートル、中心気圧960ヘクトパスカルという異常なまでの気象状況を呈していった。洞爺丸は、この日午後3時15分、1314名の旅客・乗員と12両の貨車を積載して出港準備を完了した。しかし天候の悪化に加え、停電による桟橋可動橋の巻き上げが不能となったため、近藤平市船長は、テケミ（出港見合わせ）をおこなったが、午後6時30分出港を決意し、同39分、函館桟橋を離れたのである（坂本幸四郎『青函連絡船』）。
            だが台風は衰えるどころか、風速が30メートルをこえ、45メートルに達する突風に遭遇したため、運航を見合わせ、錨泊避難することにした。風浪はさらに増し、激しい船体動揺のなかで、車両航送用の船尾開口部から車両甲板に海水が浸入、機関室や汽罐室まで浸水して石炭庫の石炭が流失し焚火不能、蒸気圧を失って操船不能という最悪の事態を招くに至ったのである。午後10時12分、船長は「両舷機不能のため漂流中」と打電、さらに「SOS、洞爺丸函館港外青灯より二六七度八ケーブルの地点に座礁せり」と発信したのを最後に通信が途絶えた（同前）。
            この台風の威力が並々ならぬものであったことは、市街地での被害からも明らかである。家屋の被害は全壊が180戸、半壊が203戸、小中学校の一部も屋根を飛ばされ臨時休校するところもあった。そのほか、農作物被害や連絡船以外の船舶の被害など、被害総額は9億円を突破した（昭和29年10月2日・5日付け「函新」）。
            洞爺丸の横転沈没は、午後10時20分頃と推定されるが、貨物船の第十一青函丸は、それより早い午後8時頃、北見丸は午後10時20分頃、十勝丸は午後10時43分頃、日高丸は午後11時40分頃であったといわれる（前掲『青函連絡船』）。
            遺体の収容は難行をきわめた。とくに貨物船関係の遺体引揚作業は顧みられず、事故後10日目にしてようやく着手された。「いくら職員だからといってあまりな仕打ちです」という遺族の悲痛な叫びが報道された（昭和29年10月6日付け「函新」）。
            当時の新聞には、この海難事故にまつわるエピソードが紹介されている。自分の救命胴衣を譲って犠牲となった外国人宣教師の話（昭和29年10月11日「函新」）、火葬場に運ばれる途中で息を吹き返した男性の話（同29年10月4日付け「函新」）、貨物船に乗っていた乗船名簿のないカツギ屋たちの話（同前）、と事故の陰にはさまざまな人間模様がみてとれる。
            一方、大森町の慰霊堂に収容されたまま、引き取りのない身元不明の遺体についての一覧が、新聞に掲載された（昭和29年10月7日付け「函新」）。身体的特徴や遺留品が記されているが、なかには頭部や顔が変形して不明という遺体や、妊娠中の女性の遺体などもあった。
            連絡船と生命を共にした遺体は、函館市火葬場だけでは間に合わず、沈没現場に近い上磯町七重浜に臨時の野天火葬場を設け、荼毘（だび）にふされた。
            昭和30年8月25日に、七重浜に建立された遭難者慰霊碑の除幕式がおこなわれ、翌26日には元町の東本願寺で、洞爺丸ほか4隻の連絡船関係者の合同慰霊法要がおこなわれた。その模様を「胸をうつ遺族のすすり泣きの声は高まるばかり」と新聞は報道している（昭和30年8月27日付け「道新」）。
            事故からほぼ1年後の昭和30年9月22日、函館地方海難審判庁では、立すいの余地なくつめかけた報道陣や遺族が傍聴するなか、裁決がおこなわれた。それは「船長の運航に関する職務上の過失に起因して発生したものであるが、本船の船体構造および連絡船の運航管理が適当でなかったこともその一因である」という内容で、当時の十河信二国鉄総裁に勧告が申し渡された（昭和30年9月23日付け「道新」）。
            この悲惨な事故の教訓を得て、その後新造された連絡船の船尾開口部には、扉が設けられ、燃料も石炭から軽油を用いたディーゼルエンジンへとかわった。
            一方、天候に左右されずに、安全で大量に、しかも迅速な輸送力をめざして計画された、本州と北海道を結ぶ海底トンネルの構想は、洞爺丸をはじめとする連絡船の海難事故を契機として、その建設計画が一気に浮上し、進められることになった（コラム55参照）。（桜井健治）
    """.trimIndent().replace(Regex("(?m)^"), "　"),
    thumbnailImage = Res.drawable.toyamaru_typhoon_thumbnail,
    mapImages = null,
    photoImages = persistentMapOf(
        Res.drawable.toyamaru_typhoon_photo_image1 to "https://archives.c.fun.ac.jp/photos/ph003119/0006",
    ),
    postcardImages = persistentMapOf(
        Res.drawable.toyamaru_typhoon_postcard_image1 to "https://archives.c.fun.ac.jp/postcards/pc000348/0002",
        Res.drawable.toyamaru_typhoon_postcard_image2 to "https://archives.c.fun.ac.jp/postcards/pc000348/0001",
    ),
)

val zenikamesawamuraMerger = MajorEvent(
    gregorianCalender = 1966,
    japaneseCalendar = "昭和41年",
    eventType = PopulationEventType.Merger,
    id = PopulationRelatedEvent.ZenikamesawamuraMerger,
    name = "銭亀沢村合併",
    exception = """
            根崎温泉地区の函館市への編入以後は、全国的に市町村合併が促進された昭和28年から29年にかけても銭亀沢村との合併はあまり話題にならなかった。ちなみに、昭和29年には全国で176の市が誕生している（昭和30年版『日本都市年鑑』）。経済の高度成長期と命名されることになる昭和30年代に入ると、住民の生活や環境に対する意識も高くなった。函館市との合併時に銭亀沢村の教育長であった吉田正明の回想によると、とくに上水道の敷設要望が強かったことをあげている。昭和28年から29年に銭亀地区と志海苔地区に簡易上水道を敷設したが、あまり衛生的とはいえず、ある村議に「村長、今の簡易水道は、水を汲んでおくとボーフラがわいているが、われわれは金魚ではないんだ。ボーフラを喰って生きるつもりはない。どうするつもりか」と質問され、答弁に手間取って休憩時間となり、その時蛯子村長は「イヤイヤあれは飲み方があるんだよ、水を飲むときはまず瓶の縁をトントンと叩くとボーフラは瓶の底に沈むから、そのとき汲んで飲むとよい、家でもそうしている」とはなしてうやむやとなったそうである（吉田正明「銭亀沢村時代の様々なこと」『地域史研究はこだて』第19号）。
            また、函館市域から銭亀沢村域にかけて用地買収されて、昭和36年4月に函館空港が開設されたことも、合併を考える方向に進んでいく契機となったようで、村議会議員の改選後の昭和38年6月に「自治振興調査特別委員会」が設置され、合併も視野に入れることとなった。行政を担当する側からみると、その要因として第一番にあげられるのは、農業や漁業などの第1次産業の不振による税収の落ち込みで、財政力指数が他町村と比べて非常に弱く、昭和40年度は、上磯町0.63、大野町0.19、七飯町0.33、亀田町0.33、銭亀沢村0.17であった（前掲「銭亀沢村時代の様々なこと」）。
            昭和39年になると、函館市議会、銭亀沢村議会とも合併調査特別委員会を設置して諸問題を検討していくことで合意し、銭亀沢村の委員は、班を編制して北海道内外の既合併都市調査をおこなった。また、昭和39年9月から11月にかけて村内の部落会をはじめ各種団体からの要望の取りまとめがおこなわれ、昭和40年9月には村の「地域振興計画（銭亀沢村振興五カ年計画）」が作られた。この計画の大要を函館市議会が了承し、市村合併協議会の設置について函館市から申し入れがなされた。翌41年1月、村としての説明会が10会場で部落懇談会として開催されたが、時期尚早という意見が多い会場が多く、もっと民意を反映した形での合併を望むというものとなった。
            昭和41年2月、函館市と銭亀沢村は合併協議会を設置し、具体的に合併を進めることとなった。当初の会合で合併予定日を10月1日と決めて作業を進めていたが、住民への説明会での時期尚早論を反映して、6月16日には銭亀沢村と村議会へ住民の意思を尊重し住民投票を求める陳情書が提出された。このため、村は署名者があった志海苔町、銭亀町、湊町、古川町、石崎町の5地区で説明会を開催したが、これらの地区では合併に反対する意向が根強かった。さらに銭亀沢村郷憂会という組織が結成され、約1800人（有権者人の3分の1）の会員を集め、8月2日、会長以下役員連名で渡島支庁長へ合併を慎重に進めるよう反対陳情書を提出した。合併協議会で進めている内容を村民に情報公開し、合併の賛否は村民の意志に委ねるように配慮いただきたいというものであった。
            しかし、8月22日に銭亀沢村の合併特別委員会が開かれ、合併期日を12月1日とすることを賛成10反対5で決定し、25日の市村合併協議会でも了承され、30項目にわたる合併協議会決定事項も議会の決定を待つだけとなった。9月14日、函館市と銭亀沢村の両議会で臨時議会が開かれ、議案「亀田郡銭亀沢村を廃し函館市に編入することについて」が賛成多数で可決された。この時、銭亀沢村議会では蛯子綱太郎、伊藤吉治郎、杉下勝明の3議員が同議案に反対した。「地域振興計画」の内容は銭亀沢村が漁村であることが考えられていないこと、合併について説明が充分でないこと、自治体が大きくなると住民の意志を政治に反映することが難しくなることなどをその理由としている。函館市でも井上一議員が日本共産党を代表して合併の理由、その意義が明確でないとして反対している。
            両議会で合併案が可決されると、早速北海道に合併申請書が提出され、北海道議会総務常任委員会に付託された。10月12日道議会が開催され、総務委員長は「両市村は地形的にも、また社会的にも不離一体の関係にあり、かつ、合併によって自治行政の効率的な運営と住民福祉の向上が期待されますので適当と認め原案可決」と報告、その報告の通り可決された。その後自治省における手続を経て11月22日自治省告示第174号として告示され、11月30日に村役場廃庁式が銭亀沢中学校でおこなわれて、12月1日、銭亀沢村は函館市に編入された。
            昭和42年度から「市地域振興計画」は実施に移され、村役場から函館市銭亀沢支所となった庁舎は、43年1月に新築落成し、上水道も45年度までに敷設された。この「市村建設計画」で、もっとも事業費が計上されたのは、用地買収を含む道路整備費で、ついでコンクリートブロック投入などの沿岸漁場整備の浅海増殖設備費、水道施設費となっていた（『函館市史』銭亀沢編参照）。
    """.trimIndent().replace(Regex("(?m)^"), "　"),
    thumbnailImage = Res.drawable.zenikamesawamura_merger_thumbnail,
    mapImages = null,
    photoImages = persistentMapOf(
        Res.drawable.zenikamesawamura_merger_photo_image1 to "https://archives.c.fun.ac.jp/photos/ph004748/0001",
    ),
    postcardImages = null,
)

val kamedasiMerger = MajorEvent(
    gregorianCalender = 1973,
    japaneseCalendar = "昭和48年",
    eventType = PopulationEventType.Merger,
    id = PopulationRelatedEvent.KamedasiMerger,
    name = "亀田市合併",
    exception = """
            字港地区の編入後も函館市から合併を働きかけられていた亀田村では、昭和28年には亀田村議会が村長を不信任し（「市村合併問題にからみ村内が混乱の極に達しているのに首長として何らの収集策を講じない」として）、対抗策として村長が村議会を解散、改選後村長が信任されるという動きもあったが、この時は村は合併しない方向で合併問題が終息した。
            その後も函館市からの働きかけは続けられたが、合併問題が進捗するのは、合併に対して賛成の立場を明確にした吉田政雄が亀田町長（昭和37年1月1日町制施行）に当選した昭和42年以降のことである。
            この間、亀田川の上流で取水している函館市の上水道の問題が、亀田町の住民の生活の問題としてしばしば取りあげられている。昭和32年には「水道問題で難航　亀田合併」と報じられ（7月11日付け「道新」）、昭和38年には「市　上水道の分水を検討　亀田町に一日三千トン」（9月10日付け「道新」）、「亀田町への分水　″時期尚早″で保留 市議会工営港湾委」（9月29日付け「道新」）、「上水道分水問題またお流れ　亀田町独自の事業」（10月30日付け「道新」）となり、翌年には「配水管亀田町に払い下げ市議会了承」（昭和39年11月5日付け「道新」）、昭和42年には「亀田町との行政提携　上水道分水きっかけに下水道、清掃など一体化へ」（8月6日付け「道新」）となってこの問題は一応の決着をみている。このことは、亀田町にとって昭和40年代の始めから顕著になった人口増により、住民の生活環境整備が追いつけない状況が深刻になっていたことの現れでもあった。「北海道新聞」は、「四万人の亀田町」という特集を5回にわたって連載、「（人口）三年間で急カーブ」「追われる教室、道路」「神山の衛生センター」「悩みの都市十年計画」「突き当たる合併」を取りあげて、都市化の波に悩む亀田町の実情を伝えた（昭和43年10月8日から13日付け「道新」）。
            昭和43年9月14日に「亀田町函館市合併促進期成会」から町議会に「亀田と函館は本来唇歯輔車（しんしほしゃ）の関係にあった、これを実らせなかったのは地域住民の怠慢であったと云える」との促進要望書が提出され、12月17日には町議会で「亀田町函館市自治振興調査特別委員会」（町議会議委員全員で構成）の設置が決まり、翌44年2月から委員会が動き始めたが、町議の改選で実働は、昭和45年に持ち越され、2月4日に第1回委員会で委員長副委員長を互選し、20日には亀田町函館市両市町自治振興調査特別委員の初会合が開催された。その後亀田の委員会審議は継続して開かれ、昭和48年6月15日の委員会は38回目の委員会が開かれた。この間、亀田町は昭和46年11月1日に市制へ移行していた。
            昭和47年3月の定例市議会の市制執行方針で吉田市長は、合併問題について「種々の状勢を勘案して、四十八年秋をめどとして実現したい」と表明し、さらに翌48年3月の定例市議会でも「その後、『広報かめだ』における分析や、市内有志による陳情、署名運動などによって、大きく世論が盛りあがっていることは、ご承知のとおりであります」と述べ、自治振興調査特別委員会、総務常任委員会の結論へも期待感を表明した。
            5月15日から臨時亀田市議会が開かれ、市民注視のなかで24日、合併促進陳情書を採択するとした総務常任委委員長報告のとおり賛成18、反対7で可決した。その後両市議会で「函館市亀田市合併協議会」の設置が可決され、第1回会合は7月8日開かれ、8月24日の第11回まで協議を続け合意に達した。
            亀田市民による住民運動は、町時代から活動を続けてきた「亀田市函館市合併促進期成会」が、「我々はなぜ合併を主張するのか」とのチラシを各戸に配布して合併促進を呼びかけた。また、亀田市函館市合併を進める市民会議も結成され、「亀田函館の合併は住みよい地域づくりと明日の豊かな市民生活を目指してすすむ」などのチラシを配布し、日本社会党亀田支部は、吉田市長と市民会議の支持を表明した。一方、「合併に疑問をもつ会」も結成され、「合併には、多くの問題点があります。市民の生活を左右する合併問題は住民投票できめましょう」とのチラシを配り、9月25日には「自治体の合併については、理事者及び議会並びに市民参加のもとに処理すべきもので、合併の具体的な資料を市民個々に提示して町会毎に説明会を開催し、住民投票を実施して有権者の過半数をもって合併することが、最も民主的な方法であると固く信じて居ります」との請願をおこない、これに先立って「亀田市住民投票実施条例」制定請求書を提出していた。しかし、函館市議会は9月3日に、亀田市議会は9月7日に合併議案を可決しており、10月27日に亀田市議会は臨時市議会を開き、自治体合併に関する亀田市住民投票実施条例案は否決された。
            昭和48（1973）年12月1日に亀田市は廃止され、その区域は函館市に編入された。「北海道新聞」は、この日の模様を「函館、亀田両市の合併により、1日から亀田市が消滅、道南の地に30万都市函館が誕生した。この日の新函館市は朝から亀田支所開所式、辞令交付、事務引継ぎ書調印式、祝賀パーティー、児童生徒交歓会と、あわただしい動き。……市内には″合併祝賀″の看板ひとつ見られなかった。″函館、亀田はもともと一つ″という意識があるためか、市民の受け取り方も冷静そのもので、お祭り気分の市当局者と好対照を見せていた」と報じた（昭和48年12月2日付け「道新」）。
            これまでの合併で函館市域面積がどのように変わったかを示しておく（表2－1、図2－5）。人口については昭和14年の湯川町が約1万人、昭和41年の銭亀沢村1万人弱、昭和48年の亀田市6万6000人が増加した。函館市はすでに昭和6年で人口20万の都市となっていたが、昭和48年の亀田市との合併で、人口は30万490人となった。その後、平成12（2000）年の国勢調査では、28万7648人（本庁管内10万159、湯川支所管内5万7449、銭亀沢支所管内8516、亀田支所管内12万1524）となっている。
    """.trimIndent().replace(Regex("(?m)^"), "　"),
    thumbnailImage = Res.drawable.kamedasi_merger_thumbnail,
    mapImages = null,
    photoImages = persistentMapOf(
        Res.drawable.kamedasi_merger_photo_image1 to "https://archives.c.fun.ac.jp/photos/ph004869/0001",
    ),
    postcardImages = null,
)
