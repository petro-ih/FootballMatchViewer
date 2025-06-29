package com.footballmatchviewer.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.footballmatchviewer.data.storage.entity.MatchEntity
import com.footballmatchviewer.data.storage.entity.TeamEntity

@Database(
    entities = [
        MatchEntity::class,
        TeamEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
    abstract fun teamDao(): TeamDao
}