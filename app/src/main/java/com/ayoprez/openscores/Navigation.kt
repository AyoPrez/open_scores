package com.ayoprez.openscores

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SportSelectionScreen.route) {
        composable(route = Screen.SportSelectionScreen.route) {
            SportSelectionScreen(navController = navController)
        }
        composable(
            route = Screen.GameScoresScreen.route,
            arguments = listOf(
                navArgument(SPORT_ARG_KEY) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            GameScoresScreen(navController = navController, it.arguments?.getString(SPORT_ARG_KEY))
        }
    }
}