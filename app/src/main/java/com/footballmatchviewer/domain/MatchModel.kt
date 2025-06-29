package com.footballmatchviewer.domain

import java.util.Date

data class MatchModel(
    val id: Long,
    val homeTeam: Team,
    val awayTeam: Team,
    val timeStamp: Date,
) {
    data class Team(
        val id: Long,
        val name: String,
        val icon: String,
        val goals: Int
    )
}

