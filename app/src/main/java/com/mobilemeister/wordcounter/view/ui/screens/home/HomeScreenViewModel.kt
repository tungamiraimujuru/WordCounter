package com.mobilemeister.wordcounter.view.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilemeister.wordcounter.util.scrabbleLetterScores
import com.mobilemeister.wordcounter.util.wordPattern
import com.mobilemeister.wordcounter.view.ui.screens.home.HomeScreenState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class HomeScreenViewModel @Inject constructor(@Named("io") private val ioContext: CoroutineContext) :
    ViewModel() {

    private var _uiState = mutableStateOf(HomeScreenUIState())
    val uiState: State<HomeScreenUIState> = _uiState

    fun setFileNameAndContents(result: Pair<String, String>) {

        updateFileName(result.first)

        processFileContents(result.second)
    }

    fun updateFileName(fileName: String) {
        _uiState.value = _uiState.value.copy(fileName = fileName)
    }

    fun processFileContents(fileInfo: String) {

        _uiState.value = _uiState.value.copy(screenState = HomeScreenState.Loading)

        viewModelScope.launch(ioContext) {

            val startTime = System.currentTimeMillis()

            val matcher = wordPattern.matcher(fileInfo.lowercase())
            val words = mutableListOf<String>()

            while (matcher.find()) {
                words.add(matcher.group())
            }

            val wordCounts = words.groupingBy { it }.eachCount()
            val mostFrequentWord = wordCounts.maxByOrNull { it.value }?.key ?: "N/A"
            val mostFrequentWordCount = wordCounts[mostFrequentWord] ?: 0

            val sevenCharWords = words.filter { it.length == 7 }
            val sevenCharWordCounts = sevenCharWords.groupingBy { it }.eachCount()
            val mostFrequentSevenCharWord =
                sevenCharWordCounts.maxByOrNull { it.value }?.key ?: "N/A"
            val mostFrequentSevenCharWordCount = sevenCharWordCounts[mostFrequentSevenCharWord] ?: 0

            val wordScores = words.associateWith { calculateScore(it) }
            val highestScoringWord = wordScores.maxByOrNull { it.value }?.key ?: "N/A"
            val highestScore = wordScores[highestScoringWord] ?: 0

            val endTime = System.currentTimeMillis()
            val elapsedTime = endTime - startTime

            _uiState.value = _uiState.value.copy(
                mostFrequentWord = mostFrequentWord,
                mostFrequentWordCount = mostFrequentWordCount,
                mostFrequentSevenCharWord = mostFrequentSevenCharWord,
                mostFrequentSevenCharWordCount = mostFrequentSevenCharWordCount,
                highestScoringWord = highestScoringWord,
                highestScore = highestScore,
                processingTime = elapsedTime,
                screenState = Success
            )
        }
    }

    fun calculateScore(word: String): Int {
        return word.sumOf { scrabbleLetterScores[it.uppercaseChar()] ?: 0 }
    }
}