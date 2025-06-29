package com.footballmatchviewer.data

import com.footballmatchviewer.domain.MatchModel

interface MatchesDataSource {
    suspend fun getMatches(league: Int, season: Int) : List<MatchModel>
}