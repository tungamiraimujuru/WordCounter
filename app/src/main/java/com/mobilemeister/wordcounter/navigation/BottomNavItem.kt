package com.mobilemeister.wordcounter.navigation

import com.mobilemeister.wordcounter.R

sealed class BottomNavItem(val route: String, val iconID: Int, val title: String) {
    data object Home : BottomNavItem("home", R.drawable.ic_home_icon, "Home")
}