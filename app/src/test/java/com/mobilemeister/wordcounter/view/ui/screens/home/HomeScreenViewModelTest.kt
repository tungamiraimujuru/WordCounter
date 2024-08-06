package com.mobilemeister.wordcounter.view.ui.screens.home

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeScreenViewModelTest {

    private lateinit var viewModel: HomeScreenViewModel

    @Before
    fun setUp() {
        viewModel = HomeScreenViewModel(Dispatchers.Unconfined)
    }

    @Test
    fun `given new file name when updateFileName is called then uiState is updated`() {

        val newFileName = "newFile.txt"

        viewModel.updateFileName(newFileName)

        assertEquals(newFileName, viewModel.uiState.value.fileName)
    }

    @Test
    fun `given fileInfo when processFileContents is called then uiState is updated correctly`() = runTest {

        val fileInfo = "This is a test file with test content. Test file content."

        viewModel.processFileContents(fileInfo)

        advanceUntilIdle()

        val uiState = viewModel.uiState.value

        assertEquals(HomeScreenState.Success, uiState.screenState)
        assertEquals("test", uiState.mostFrequentWord)
        assertEquals(3, uiState.mostFrequentWordCount)
        assertEquals("content", uiState.mostFrequentSevenCharWord)
        assertEquals(2, uiState.mostFrequentSevenCharWordCount)
        assertEquals("with", uiState.highestScoringWord)
    }

    @Test
    fun `given a word when all the letters are known then calculate the scrabble score`() {
        val word = "KOTLIN"
        val expectedScore = 10 // K=5, O=1, T=1, L=1, I=1, N=1
        val score = viewModel.calculateScore(word)
        assertEquals(expectedScore, score)
    }

    @Test
    fun `given a word when this input is mixed-case letters then calculate the score`() {
        val word = "Kotlin"
        val expectedScore = 10 // K=5, o=1, t=1, l=1, i=1, n=1
        val score = viewModel.calculateScore(word)
        assertEquals(expectedScore, score)
    }

    @Test
    fun `test calculateScore with unknown characters`() {
        val word = "Kotlin!"
        val expectedScore = 10 // K=5, o=1, t=1, l=1, i=1, n=1, !=0
        val score = viewModel.calculateScore(word)
        assertEquals(expectedScore, score)
    }

    @Test
    fun `given an empty string, when the input is empty, then calculate the score`() {
        val word = ""
        val expectedScore = 0
        val score = viewModel.calculateScore(word)
        assertEquals(expectedScore, score)
    }

    @Test
    fun `given a numeric string, when the input is not a word, then calculate the score`() {
        val word = "123456"
        val expectedScore = 0
        val score = viewModel.calculateScore(word)
        assertEquals(expectedScore, score)
    }

    @Test
    fun `given a single character, when the input is a single character, then calculate the score`() {
        val word = "A"
        val expectedScore = 1 // A=1
        val score = viewModel.calculateScore(word)
        assertEquals(expectedScore, score)
    }

    @Test
    fun `given a single numeric character, when the input is a single numeric character, then calculate the score`() {
        val word = "1"
        val expectedScore = 0 // 1=0
        val score = viewModel.calculateScore(word)
        assertEquals(expectedScore, score)
    }
}