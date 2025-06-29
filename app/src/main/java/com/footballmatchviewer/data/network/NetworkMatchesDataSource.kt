package com.footballmatchviewer.data.network

import com.footballmatchviewer.data.MatchesDataSource
import com.footballmatchviewer.domain.MatchModel
import jakarta.inject.Inject

internal class NetworkMatchesDataSource @Inject constructor(
    private val mapper: NetworkMapper,
    private val footballApi: FootballApi
) : MatchesDataSource {

    override suspend fun getMatches(
        league: Int,
        season: Int
    ): List<MatchModel> = footballApi.getFixtures(
        season = season,
        league = league
    ).let(mapper::mapFromNetwork)
}