package com.mobilemeister.wordcounter.view.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilemeister.wordcounter.view.ui.theme.WordCounterTheme

@Composable
fun UploadFile(onClick: () -> Unit, fileName: String, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Row(modifier = Modifier.weight(1f)) {

            Text("File : ")

            Text(text = fileName)
        }

        OutlinedButton(modifier = Modifier.weight(1f), onClick = {
            onClick()
        }) {
            Text("Upload")
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    WordCounterTheme {
        UploadFile(onClick = {}, fileName = "test.txt")
    }
}
