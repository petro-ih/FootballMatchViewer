package com.footballmatchviewer.data.storage.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MatchWithTeams(
    @Embedded val match: MatchEntity,

    @Relation(
        parentColumn = "homeTeamId",
        entityColumn = "id"
    )
    val homeTeam: TeamEntity,

    @Relation(
        parentColumn = "awayTeamId",
        entityColumn = "id"
    )
    val awayTeam: TeamEntity
)