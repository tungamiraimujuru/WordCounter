package com.mobilemeister.wordcounter.view.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilemeister.wordcounter.view.ui.screens.home.HomeScreenState.Error
import com.mobilemeister.wordcounter.view.ui.screens.home.HomeScreenState.Loading
import com.mobilemeister.wordcounter.view.ui.screens.home.HomeScreenState.Success
import com.mobilemeister.wordcounter.view.ui.theme.WordCounterTheme

@Composable
fun HomeScreenContent(
    onClick: () -> Unit,
    uiState: HomeScreenUIState,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState.screenState) {

            is Loading -> {

                CircularProgressIndicator()

                Spacer(modifier =Modifier.height(16.dp))

                Text("Busy Loading")
            }

            is Success -> {

                Spacer(modifier =Modifier.height(56.dp))

                UploadFile(onClick = onClick, fileName = uiState.fileName)

                ProcessedResults(
                    uiState = uiState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            is Error -> {
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    WordCounterTheme {
        HomeScreenContent(onClick = {}, uiState = HomeScreenUIState())
    }
}