package com.ayoprez.openscores.ui

const val SPORT_ID_ARG_KEY = "sportId"
const val SPORTS_IDS_ARG_KEY = "sportIds"
const val GAME_ID_ARG_KEY = "game"

sealed class ScreenNavigation(val route: String) {
    object SportSelectionScreen: ScreenNavigation("sport_selection")

    object GameScoresScreen: ScreenNavigation("game_scores/{$SPORT_ID_ARG_KEY}") {
        fun passValue(sportId: String): String {
            return this.route.replace(oldValue = "{$SPORT_ID_ARG_KEY}", newValue = sportId)
        }
        fun passValues(sportIds: Array<String>): String {
            return this.route.replace(oldValue = "{$SPORT_ID_ARG_KEY}", newValue = sportIds.joinToString(separator = ", "))
        }
    }

    object GameDetailsScreen: ScreenNavigation("game_details/{$GAME_ID_ARG_KEY}") {
        fun passValue(id: String): String {
            return this.route.replace(oldValue = "{$GAME_ID_ARG_KEY}", newValue = id)
        }
    }
}
