package com.footballmatchviewer.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey val id: String,
    val homeTeam: String,
    val awayTeam: String,
    val date: String,
    val score: String? = null
)