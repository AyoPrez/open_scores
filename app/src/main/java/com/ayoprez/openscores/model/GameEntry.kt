package com.ayoprez.openscores.model

data class GameEntry(
    val id: String,
    val localTeamName: String,
    val localTeamScore: Int,
    val visitTeamName: String,
    val visitTeamScore: Int
)
