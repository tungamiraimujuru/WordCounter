package com.mobilemeister.wordcounter.view.ui.screens.home

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mobilemeister.wordcounter.view.ui.theme.WordCounterTheme
import java.io.BufferedReader
import java.io.InputStreamReader
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Home(modifier: Modifier = Modifier, viewModel: HomeScreenViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val uiState = viewModel.uiState.value

    val fileChooserLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val result = readTextFileFromUri(context, it)
            viewModel.setFileNameAndContents(result)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        HomeScreenContent(onClick = {
            fileChooserLauncher.launch("text/plain")
        }, uiState = uiState)
    }
}

fun readTextFileFromUri(context: Context, uri: Uri): Pair<String, String> {
    val contentResolver = context.contentResolver
    var fileName = "Unknown"
    var fileContent = ""

    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        fileName = cursor.getString(nameIndex)
    }

    contentResolver.openInputStream(uri)?.use { inputStream ->
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            fileContent = reader.readText()
        }
    }

    return Pair(fileName, fileContent)
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    WordCounterTheme {
        Home()
    }
}