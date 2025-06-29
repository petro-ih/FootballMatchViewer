package com.footballmatchviewer.data

import com.footballmatchviewer.domain.MatchModel

interface MatchesDataSource {
    suspend fun getMatches(league: Int, season: Int, forceReload: Boolean = false) : List<MatchModel>
    suspend fun saveMatches(matches: List<MatchModel>) = Unit
}