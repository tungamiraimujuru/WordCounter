package com.mobilemeister.wordcounter.view.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilemeister.wordcounter.view.ui.theme.WordCounterTheme

@Composable
fun ProcessedResults(uiState: HomeScreenUIState, modifier: Modifier = Modifier) {

    val mostFrequentWord =
        "Most frequent word: ${uiState.mostFrequentWord} occurred ${uiState.mostFrequentWordCount} times"

    val mostFrequentSevenCharWord =
        "Most frequent 7-character word:  ${uiState.mostFrequentSevenCharWord} occurred ${uiState.mostFrequentSevenCharWordCount} times"

    val highestScoringWord =
        "Highest scoring word: ${uiState.highestScoringWord} with a score of ${uiState.highestScore}"

    val processingTime = "Processing time: ${uiState.processingTime} ms"

    Column(modifier = modifier.fillMaxSize()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = mostFrequentWord)

        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(mostFrequentSevenCharWord)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(highestScoringWord)

        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(processingTime)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    WordCounterTheme {
        ProcessedResults(uiState = HomeScreenUIState())
    }
}