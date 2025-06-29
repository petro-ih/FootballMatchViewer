package com.footballmatchviewer.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.footballmatchviewer.data.storage.entity.MatchEntity
import com.footballmatchviewer.data.storage.entity.MatchWithTeams

@Dao
interface MatchDao {
    @Query("SELECT * FROM matches ORDER BY date DESC")
    suspend fun getAllMatches(): List<MatchWithTeams>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatches(matches: List<MatchEntity>)

    @Query("DELETE FROM matches")
    suspend fun clearAll()
}