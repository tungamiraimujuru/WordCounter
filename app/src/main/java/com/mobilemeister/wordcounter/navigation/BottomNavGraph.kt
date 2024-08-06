package com.mobilemeister.wordcounter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobilemeister.wordcounter.view.ui.screens.home.Home


@Composable
fun BottomNavGraph(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {

        composable(route = BottomNavItem.Home.route) {
            Home(modifier = modifier)
        }
    }
}