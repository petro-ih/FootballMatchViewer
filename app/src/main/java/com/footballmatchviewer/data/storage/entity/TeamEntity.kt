package com.footballmatchviewer.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val icon: String,
)