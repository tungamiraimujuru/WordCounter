package com.mobilemeister.wordcounter.view.ui.screens.home

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock

class HomeScreenUIStateTest {

    @Test
    fun `given default values when creating instance then properties are initialised correctly`() {

        val defaultState = HomeScreenUIState()

        assertEquals("", defaultState.fileName)
        assertEquals("", defaultState.bookText)
        assertEquals("", defaultState.mostFrequentWord)
        assertEquals(0, defaultState.mostFrequentWordCount)
        assertEquals("", defaultState.mostFrequentSevenCharWord)
        assertEquals(0, defaultState.mostFrequentSevenCharWordCount)
        assertEquals("", defaultState.highestScoringWord)
        assertEquals(0, defaultState.highestScore)
        assertEquals(0L, defaultState.processingTime)
        assertEquals(HomeScreenState.Success, defaultState.screenState)
    }

    @Test
    fun `given new values when updating properties then properties are updated correctly`() {

        val initialState = HomeScreenUIState()
        val newFileName = "newFile.txt"
        val newBookText = "New book text"
        val newMostFrequentWord = "newWord"
        val newMostFrequentWordCount = 10
        val newMostFrequentSevenCharWord = "newSeven"
        val newMostFrequentSevenCharWordCount = 5
        val newHighestScoringWord = "newHighScore"
        val newHighestScore = 50
        val newProcessingTime = 1000L
        val newScreenState = mock(HomeScreenState::class.java)

        val updatedState = initialState.copy(
            fileName = newFileName,
            bookText = newBookText,
            mostFrequentWord = newMostFrequentWord,
            mostFrequentWordCount = newMostFrequentWordCount,
            mostFrequentSevenCharWord = newMostFrequentSevenCharWord,
            mostFrequentSevenCharWordCount = newMostFrequentSevenCharWordCount,
            highestScoringWord = newHighestScoringWord,
            highestScore = newHighestScore,
            processingTime = newProcessingTime,
            screenState = newScreenState
        )

        assertEquals(newFileName, updatedState.fileName)
        assertEquals(newBookText, updatedState.bookText)
        assertEquals(newMostFrequentWord, updatedState.mostFrequentWord)
        assertEquals(newMostFrequentWordCount, updatedState.mostFrequentWordCount)
        assertEquals(newMostFrequentSevenCharWord, updatedState.mostFrequentSevenCharWord)
        assertEquals(newMostFrequentSevenCharWordCount, updatedState.mostFrequentSevenCharWordCount)
        assertEquals(newHighestScoringWord, updatedState.highestScoringWord)
        assertEquals(newHighestScore, updatedState.highestScore)
        assertEquals(newProcessingTime, updatedState.processingTime)
        assertEquals(newScreenState, updatedState.screenState)
    }
}