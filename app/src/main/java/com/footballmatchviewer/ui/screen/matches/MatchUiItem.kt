package com.footballmatchviewer.ui.screen.matches

data class MatchUiItem (
    val homeTeam: Team,
    val awayTeam: Team,
    val date: String,
    val time: String,
) {
    data class Team(
        val name: String,
        val icon: String,
        val goals: Int
    )
}