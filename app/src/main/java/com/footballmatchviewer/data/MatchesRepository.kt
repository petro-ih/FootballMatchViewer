package com.footballmatchviewer.data

import com.footballmatchviewer.data.network.NetworkModule
import com.footballmatchviewer.domain.MatchModel
import javax.inject.Inject

class MatchesRepository @Inject internal constructor(
    @NetworkModule.Network
    val networkDataSource: MatchesDataSource
) : MatchesDataSource {

    override suspend fun getMatches(
        league: Int,
        season: Int
    ): List<MatchModel> {
        // todo - use storage data source
        return networkDataSource.getMatches(league, season)
    }
}