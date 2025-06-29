package com.footballmatchviewer.data.storage.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "matches",
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["homeTeamId"]
        ),
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["awayTeamId"]
        )
    ]
)
data class MatchEntity(
    @PrimaryKey val id: Long,
    val homeTeamId: Long,
    val awayTeamId: Long,
    val date: Long,
    val homeTeamGoals: Int,
    val awayTeamGoals: Int,
)