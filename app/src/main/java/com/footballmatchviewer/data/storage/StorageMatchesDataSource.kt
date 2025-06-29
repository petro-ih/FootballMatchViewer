package com.footballmatchviewer.data.storage

import com.footballmatchviewer.data.MatchesDataSource
import com.footballmatchviewer.domain.MatchModel
import javax.inject.Inject

class StorageMatchesDataSource @Inject constructor(
    private val teamDao: TeamDao,
    private val matchDao: MatchDao,
    private val mapper: StorageMapper,
) : MatchesDataSource {

    override suspend fun getMatches(
        league: Int,
        season: Int,
        forceReload: Boolean
    ): List<MatchModel> {
        return matchDao.getAllMatches().map(mapper::mapMatchEntityToModel)
    }

    override suspend fun saveMatches(matches: List<MatchModel>) {
        val teams = matches
            .flatMap { listOf(it.homeTeam, it.awayTeam) }
            .distinctBy { it.id }
            .map(mapper::mapTeamModelToTeamEntity)

        val matches = matches.map(mapper::mapMatchModelToMatchEntity)

        teamDao.insertTeams(teams)
        matchDao.insertMatches(matches)
    }
}