package com.footballmatchviewer.domain

import java.util.Date

data class MatchModel(
    val homeTeam: Team,
    val awayTeam: Team,
    val timeStamp: Date,
) {
    data class Team(
        val name: String,
        val icon: String,
        val goals: Int
    )
}

