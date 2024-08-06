package com.mobilemeister.wordcounter.view.ui.screens.home

import com.mobilemeister.wordcounter.view.ui.screens.home.HomeScreenState.*

data class HomeScreenUIState(
    val fileName: String = "",
    val bookText: String = "",
    val mostFrequentWord: String = "",
    val mostFrequentWordCount: Int = 0,
    val mostFrequentSevenCharWord: String = "",
    val mostFrequentSevenCharWordCount : Int = 0,
    val highestScoringWord: String = "",
    val highestScore: Int = 0,
    val processingTime : Long = 0,
    val screenState: HomeScreenState = Success
)
