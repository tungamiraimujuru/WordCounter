package com.mobilemeister.wordcounter.view.ui.screens.home

sealed class HomeScreenState {

    data object Loading : HomeScreenState()
    data object Error : HomeScreenState()
    data object Success : HomeScreenState()
}