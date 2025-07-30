package org.example.project.ui.feature.population

// @Composable
// fun HistoricalEventDemoScreen() {
//    val viewModel: PopulationViewModel = koinViewModel()
//    val uiState by viewModel.uiState.collectAsState()
//
//    var filteredEvent by remember { mutableStateOf<List<HistoricalEvent>>(emptyList()) }
//
//    if (uiState.isLoadingHistoricalEvents) {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center,
//        ) {
//            CircularProgressIndicator()
//        }
//        return
//    }
//
//    uiState.historicalEventsError?.let { error ->
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center,
//        ) {
//            BodyMediumText(text = "Error: $error")
//        }
//        return
//    }
//
//    // 初期値設定
//    if (filteredEvent.isEmpty() && uiState.historicalEvents.isNotEmpty()) {
//        filteredEvent = uiState.historicalEvents
//    }
//
//    Column {
//        Row {
//            Button(
//                onClick = {
//                    filteredEvent = uiState.historicalEvents.filter {
//                        it.text.contains("大火") || it.source1?.contains("大火") == true
//                    }
//                },
//            ) {
//                BodyMediumText(
//                    text = "大火",
//                )
//            }
//            Button(
//                onClick = {
//                    filteredEvent = uiState.historicalEvents.filter {
//                        it.text.contains("戦争") || it.source1?.contains("戦争") == true
//                    }
//                },
//            ) {
//                BodyMediumText(
//                    text = "戦争",
//                )
//            }
//            Button(
//                onClick = {
//                    filteredEvent = uiState.historicalEvents
//                },
//            ) {
//                BodyMediumText(
//                    text = "全て",
//                )
//            }
//        }
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(20.dp),
//        ) {
//            items(filteredEvent) { event ->
//                HistoricalEventItem(
//                    historicalEvent = event,
//                    modifier = Modifier.fillMaxWidth(),
//                )
//            }
//        }
//    }
// }
//
// @Composable
// fun HistoricalEventItem(
//    historicalEvent: HistoricalEvent,
//    modifier: Modifier = Modifier,
// ) {
//    Column {
//        Row(
//            modifier = modifier,
//            horizontalArrangement = Arrangement.spacedBy(10.dp),
//        ) {
//            BodyMediumText(
//                text = historicalEvent.jyear,
//            )
//            BodyMediumText(
//                text = historicalEvent.year.toString(),
//            )
//            BodyMediumText(
//                text = historicalEvent.leapMonth.toString(),
//            )
//            historicalEvent.month?.let {
//                BodyMediumText(
//                    text = it.toString(),
//                )
//            }
//            historicalEvent.day?.let {
//                BodyMediumText(
//                    text = it.toString(),
//                )
//            }
//            historicalEvent.season?.let {
//                BodyMediumText(
//                    text = it,
//                )
//            }
//            BodyMediumText(
//                text = historicalEvent.eto,
//            )
//            historicalEvent.source1?.let {
//                BodyMediumText(
//                    text = it,
//                )
//            }
//            historicalEvent.source2?.let {
//                BodyMediumText(
//                    text = it,
//                )
//            }
//            historicalEvent.source3?.let {
//                BodyMediumText(
//                    text = it,
//                )
//            }
//            historicalEvent.source4?.let {
//                BodyMediumText(
//                    text = it,
//                )
//            }
//            historicalEvent.source5?.let {
//                BodyMediumText(
//                    text = it,
//                )
//            }
//            historicalEvent.source6?.let {
//                BodyMediumText(
//                    text = it,
//                )
//            }
//        }
//        BodyMediumText(
//            text = historicalEvent.text,
//        )
//    }
// }
