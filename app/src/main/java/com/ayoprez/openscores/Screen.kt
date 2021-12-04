package com.ayoprez.openscores

const val SPORT_ARG_KEY = "sport"
const val GAME_ID_ARG_KEY = "sport"

sealed class Screen(val route: String) {
    object SportSelectionScreen: Screen("sport_selection")
    object GameScoresScreen: Screen("game_scores/{$SPORT_ARG_KEY}") {
        fun passValue(sport: String): String {
            return this.route.replace(oldValue = "{$SPORT_ARG_KEY}", newValue = sport)
        }
    }
    object GameDetailsScreen: Screen("game_details/{$GAME_ID_ARG_KEY}") {
        fun passValue(id: String): String {
            return this.route.replace(oldValue = "{$GAME_ID_ARG_KEY}", newValue = id)
        }
    }
}
